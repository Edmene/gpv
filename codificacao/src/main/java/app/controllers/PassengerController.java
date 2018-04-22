package app.controllers;

import app.models.Passenger;
import app.models.User;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PassengerController extends AppController {

    public void index(){
        view("passengers", Passenger.findAll().toMaps());
    }

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

    @POST
    public void create() throws ParseException {
        User user = new User();
        user.fromMap(params1st());
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
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(param("birth_date"));
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

    @DELETE
    public void delete(){

        Passenger passenger = Passenger.findById(Integer.parseInt(getId()));
        String nome = passenger.getString("name");
        passenger.delete();
        flash("message", "User: '" + nome + "' was deleted");
        redirect(PassengerController.class);
    }

    public void newForm(){}

    @PUT
    public void alterForm(){
        Passenger passenger = Passenger.findById(Integer.parseInt(getId()));
        User user = User.findById(Integer.parseInt(getId()));
        Date date = passenger.getDate("birth_date");
        if(passenger != null){
            view("passenger", passenger, "user", user, "birth", date.toString());
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @POST
    public void update() throws ParseException{
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
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(param("birth_date"));
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
