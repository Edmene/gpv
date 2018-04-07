package app.controllers;

import app.models.City;
import app.models.State;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;

public class CityController extends AppController {
    public void index(){
        view("cities", City.findAll().toMaps());
    }

    @POST
    public void create(){
        City city = new City();
        city.fromMap(params1st());
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

    @DELETE
    public void delete(){

        City city = City.findById(Integer.parseInt(getId()));
        //Integer id = Integer.valueOf(getId());
        //Driver city = (Driver) Driver.findBySQL("SELECT * FROM DRIVERS WHERE id = ?",id).get(0);

        String name = city.getString("name");
        city.delete();
        flash("message", "Cidade: '" + name + "' was deleted");
        redirect(CityController.class);
    }

    public void show(){
        //this is to protect from URL hacking
        City city = City.findById(Integer.parseInt(getId()));
        if(city != null){
            view("city", city);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }
}
