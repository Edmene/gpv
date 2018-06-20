package app.controllers;

import app.controllers.authorization.PasswordHashing;
import app.json.CheckUserJson;
import app.models.*;
import app.utils.TransformMaskeredInput;
import com.google.gson.Gson;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class PassengerController extends GenericAppController {

    public void check(){
        if(xhr()){
            Map<String, String> map = params1st();
            if(map.keySet().size() > 0) {
                String userName = String.valueOf(map.keySet().toArray()[0]);
                CheckUserJson userJson = new CheckUserJson(User.find("name = ?", userName).size() == 0);
                Gson gson = new Gson();
                respond(gson.toJson(userJson)).contentType("application/json").status(200);
            }
            else {
                CheckUserJson userJson = new CheckUserJson(false);
                Gson gson = new Gson();
                respond(gson.toJson(userJson)).contentType("application/json").status(200);
            }
        }
    }

    @Override
    public void index(){
        view("passengers", Passenger.findAll().toMaps());
    }

    @Override
    public void show(){
        //this is to protect from URL hacking
        Passenger passenger = Passenger.findById(Integer.parseInt(getId()));
        if(passenger != null){
            view("passenger", passenger);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override @POST
    public void create() throws Exception{
        if(User.find("name = ?", param("user_name")).size() == 0) {
            User user = new User();
            user.fromMap(params1st());
            PasswordHashing passwordHashing = new PasswordHashing();
            user.set("extra", passwordHashing.getSalt());
            user.set("password", passwordHashing.hashPassword(param("password").trim()));
            user.set("name", param("user_name"));
            if (!user.save()) {
                flash("message", "Something went wrong, please  fill out all fields");
                flash("errors", user.errors());
                flash("params", params1st());
                redirect(PassengerController.class, "new_form");
            } else {
                LazyList<Model> u = User.find("name = ?", param("user_name"));
                Passenger passenger = new Passenger();
                passenger.fromMap(params1st());
                passenger.set("user_id", u.get(0).getId());

                passenger.set("telephone", TransformMaskeredInput.format(param("telephone")));
                passenger.set("cpf", TransformMaskeredInput.format(param("cpf")));
                passenger.set("rg", TransformMaskeredInput.format(param("rg")));

                LocalDate date = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(param("birth_date")));
                passenger.setDate("birth_date", date);
                //u.get(0).add(passenger);

                if (!passenger.insert()) {
                    user.setId(u.get(0).getId()).delete();
                    flash("message", "Algum erro aconteceu no cadastro");
                    flash("errors", passenger.errors());
                    flash("params", params1st());
                    redirect(PassengerController.class, "new_form");
                } else {

                    //flash("message", "Novo passageiro cadastrado: " + passenger.get("name"));
                    redirect(LoginController.class);
                }
            }
        }
        else {
            flash("message", "Nome de usuario nao disponivel");
            redirect(PassengerController.class);
        }
    }

    @Override @DELETE
    public void delete(){

        Passenger passenger = Passenger.findById(Integer.parseInt(getId()));
        String name = passenger.getString("name");
        passenger.delete();
        User.findById(Integer.parseInt(getId())).delete();
        if(session("user").toString().contentEquals(((name)))){
            redirect(LoginController.class, "logout");
        }
        else {
            flash("message", "User: '" + name + "' foi deletado");
            redirect(PassengerController.class);
        }




    }

    @Override
    public void alterForm(){
        Passenger passenger = Passenger.findById(Integer.parseInt(getId()));
        User user = User.findById(Integer.parseInt(getId()));
        LocalDate date = LocalDate.parse(passenger.get("birth_date").toString());
        if(passenger != null){
            view("passenger", passenger, "user", user, "birth", date.toString());
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override @POST
    public void update() throws Exception{
        User user = User.findById(Integer.parseInt(param("id")));
        String oldName = user.getString("name");
        user.fromMap(params1st());
        PasswordHashing passwordHashing = new PasswordHashing();
        user.set("extra", passwordHashing.getSalt());
        user.set("password", passwordHashing.hashPassword(param("password").trim()));
        user.set("name", param("user_name"));
        user.set("id", Integer.parseInt(param("id")));
        if(!user.save()){
            flash("message", "Something went wrong, please  fill out all fields");
            redirect(PassengerController.class);
        }
        else {
            Passenger passenger = new Passenger();
            passenger.fromMap(params1st());
            passenger.set("user_id", Integer.parseInt(param("id")));

            passenger.set("telephone", TransformMaskeredInput.format(param("telephone")));
            passenger.set("cpf", TransformMaskeredInput.format(param("cpf")));
            passenger.set("rg", TransformMaskeredInput.format(param("rg")));

            LocalDate date = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(param("birth_date")));
            passenger.setDate("birth_date", date);
            if (!passenger.save()) {
                flash("message", "Something went wrong, please  fill out all fields ");
                redirect(PassengerController.class);
            }
            else {
                flash("message", "Passageiro Alterado: " + passenger.get("name"));
                redirect(UserController.class, "profile", session("id"));
                if(session("user").toString().contentEquals(((oldName)))){
                    session("user", (String) passenger.get("name"));
                }
            }
        }
    }

    public void listPlan(){
        view("plans",
                PassengerDestinationWithInfo.find("passenger_id = ?", Integer.parseInt(getId())).toMaps());
    }

    @DELETE
    public void deletePlan(){
        PassengerPlans passengerPlans = (PassengerPlans) PassengerPlans.find("plan_id = ? AND" +
                " destination_id = ? AND passenger_id = ?", Integer.parseInt(param("plan_id")),
                Integer.parseInt(param("destination_id")), Integer.parseInt(param("passenger_id"))).get(0);
        passengerPlans.set("status", false);
        passengerPlans.save();

        if(PassengerPlans.find("plan_id = ? AND" +
                        " passenger_id = ? AND status IS TRUE", Integer.parseInt(param("plan_id")),
                Integer.parseInt(param("passenger_id"))).size() == 0){
            LazyList<Reservation> reservations = Reservation.find("plan_id = ? AND passenger_id = ?" +
                            " AND date IS NULL",
                    Integer.parseInt(param("plan_id")),
                    Integer.parseInt(param("passenger_id")));
            for(Reservation reservation : reservations){
                reservation.set("status", false);
            }
        }
    }
}
