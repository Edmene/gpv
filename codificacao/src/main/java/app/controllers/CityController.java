package app.controllers;

import app.controllers.authorization.Protected;
import app.models.City;
import app.models.State;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

import java.util.List;
import java.util.Map;

@Protected
public class CityController extends GenericAppController {

    @Override
    public void index(){
        view("states", State.findAll());
    }

    public void list(){
        if(xhr()){
            Map<String, String> map = params1st();
            Integer stateId = Integer.parseInt((String) map.keySet().toArray()[0]);
            if(stateId != 0) {
                LazyList<City> citiesList = City.find("state_id = ?", stateId);
                respond(citiesList.toJson(false));
            }
        }
    }

    public void cities(){
        List<Map<String, Object>> citiesList = City.find("state_id = ?",
                Integer.parseInt(getId())).toMaps();
        view("cities", citiesList, "state", State.findById(Integer.parseInt(getId())));
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
            redirect(CityController.class, "cities", city.get("state_id"));
        }
    }

    @Override
    public void newForm(){
        view("state", getId());
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
            redirect(CityController.class, "cities", city.get("state_id"));
        }
        else{
            flash("message", "Cidade alterada " + city.get("name"));
            redirect(CityController.class, "cities", city.get("state_id"));
        }
    }

    @Override @DELETE
    public void delete(){

        City city = City.findById(Integer.parseInt(getId()));
        String name = city.getString("name");
        city.delete();
        flash("message", "Cidade: '" + name + "' was deleted");
        redirect(CityController.class);
    }
}
