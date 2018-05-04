package app.controllers;

import app.models.City;
import app.models.State;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

import java.util.List;
import java.util.Map;

public class CityController extends GenericAppController {

    @Override
    public void index(){
        List<Map<String, Object>> citiesList = City.findAll().toMaps();
        for (Map<String, Object> city : citiesList){
            city.put("state", State.findById(city.get("state_id")).toMap());
        }
        view("cities", citiesList);
    }

    @Override @POST
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

    @Override
    public void newForm(){
        view("states", State.findAll().toMaps());
    }

    @Override @PUT
    public void alterForm(){
        City city = City.findById(Integer.parseInt(getId()));
        if(city != null){
            view("city", city, "states", State.findAll().toMaps());
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override @POST
    public void update(){
        City city = new City();
        city.fromMap(params1st());
        city.setInteger("state_id", Integer.parseInt(param("state_id")));
        city.set("id", Integer.parseInt(param("id")));
        if(!city.save()){
            flash("message", "Something went wrong, please restart the process");
            redirect(CityController.class);
        }
        else{
            flash("message", "Cidade alterada " + city.get("name"));
            redirect(CityController.class);
        }
    }

    @Override @DELETE
    public void delete(){

        City city = City.findById(Integer.parseInt(getId()));
        //Integer id = Integer.valueOf(getId());
        //Driver city = (Driver) Driver.findBySQL("SELECT * FROM DRIVERS WHERE id = ?",id).get(0);

        String name = city.getString("name");
        city.delete();
        flash("message", "Cidade: '" + name + "' was deleted");
        redirect(CityController.class);
    }

    @Override
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
