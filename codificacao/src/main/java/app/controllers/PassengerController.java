package app.controllers;

import app.controllers.authorization.PasswordHashing;
import app.models.Passenger;
import app.models.User;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PassengerController extends GenericAppController {

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
        User user = new User();
        user.fromMap(params1st());
        PasswordHashing passwordHashing = new PasswordHashing();
        user.set("extra", passwordHashing.getSalt());
        user.set("password", passwordHashing.hashPassword(param("password").trim()));
        user.set("name", param("user_name"));
        if(!user.save()){
            flash("message", "Something went wrong, please  fill out all fields");
            flash("errors", user.errors());
            flash("params", params1st());
            redirect(PassengerController.class, "new_form");
        }
        else {
            LazyList<Model> u = User.find("name = ?", param("user_name"));
            Passenger passenger = new Passenger();
            passenger.fromMap(params1st());
            passenger.set("user_id", u.get(0).getId());

            LocalDate date = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(param("birth_date")));
            passenger.setDate("birth_date", date);
            //u.get(0).add(passenger);

            if (!passenger.insert()) {
                user.setId(u.get(0).getId()).delete();
                flash("message", "Something went wrong, please  fill out all fields " + passenger);
                flash("errors", passenger.errors());
                flash("params", params1st());
                redirect(PassengerController.class, "new_form");
            } else {

                flash("message", "Novo passageiro cadastrado: " + passenger.get("name"));
                redirect(PassengerController.class);
            }
        }
    }

    @Override @DELETE
    public void delete(){

        Passenger passenger = Passenger.findById(Integer.parseInt(getId()));
        String nome = passenger.getString("name");
        passenger.delete();
        flash("message", "User: '" + nome + "' was deleted");
        redirect(PassengerController.class);
    }

    @Override @PUT
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
        User user = new User();
        user.fromMap(params1st());
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
            LocalDate date = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(param("birth_date")));
            passenger.setDate("birth_date", date);
            if (!passenger.save()) {
                flash("message", "Something went wrong, please  fill out all fields ");
                redirect(PassengerController.class);
            }
            else {
                flash("message", "Passageiro Alterado: " + passenger.get("name"));
                redirect(PassengerController.class);
            }
        }
    }
}
