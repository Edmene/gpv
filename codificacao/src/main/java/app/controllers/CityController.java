package app.controllers;

import app.models.City;
import app.models.State;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.POST;

public class CityController extends AppController {
    public void index(){
        view("cities", City.findAll().toMaps());
    }

    @POST
    public void create(){
        City city = new City();
        //city.fromMap(params1st());
        //String name = param("name");
        city.set("name", param("name"));
        city.setInteger("state_id", Integer.parseInt(param("state_id")));
        if(!city.save()){
            flash("message", "Something went wrong, please  fill out all fields");
            flash("errors", city.errors());
            flash("params", params1st());
            redirect(CityController.class, "new_form");
        }else{
            flash("message", "New book was added: " + city.get("name"));
            redirect(CityController.class);
        }
    }

    public void newForm(){
        view("states", State.findAll().toMaps());
    }
}
