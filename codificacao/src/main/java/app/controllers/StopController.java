package app.controllers;

import app.controllers.authorization.Protected;
import app.models.Address;
import app.models.City;
import app.models.State;
import app.models.Stop;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

import java.sql.Time;
import java.util.List;
import java.util.Map;

@Protected
public class StopController extends GenericAppController{

    //Partir do estado para cidade e depois permitir o cadastro.

    @Override
    public void index(){
        view("states", State.findAll());
    }

    public void cities(){
        List<Map<String, Object>> citiesList = City.find("state_id = ?",
                Integer.parseInt(getId())).toMaps();
        view("cities", citiesList);
    }

    public void addresses(){

        List<Map<String, Object>> addressList = Address.find("city_id = ?",
                Integer.parseInt(getId())).toMaps();
        view("addresses", addressList, "city", City.findById(Integer.parseInt(getId())));
    }

    public void stops(){
        List<Map<String, Object>> stopList = Stop.find("address_id = ?",
                Integer.parseInt(getId())).toMaps();
        for (Map<String, Object> stop : stopList){
            stop.put("address", Address.findById(stop.get("address_id")).toMap());
        }
        view("stops", stopList, "address", getId());
    }

    @Override @POST
    public void create(){
        Stop stop = new Stop();
        stop.fromMap(params1st());
        stop.setInteger("address_id", Integer.parseInt(param("address_id")));
        stop.setTime("time",Time.valueOf(param("time")+":00"));
        if(!stop.save()){
            flash("message", "Something went wrong, please  fill out all fields");
            flash("errors", stop.errors());
            flash("params", params1st());
            redirect(StopController.class, "new_form");
        }else{
            flash("message", "Nova parada cadastrada");
            redirect(StopController.class, "stops", stop.get("address_id"));
        }
    }

    @Override
    public void newForm(){
        view("address", getId());
    }

    @Override @PUT
    public void alterForm(){
        Stop stop = Stop.findById(Integer.parseInt(getId()));
        if(stop != null){
            view("stop", stop, "addresses", Address.findAll().toMaps());
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override @POST
    public void update(){
        Stop stop = new Stop();
        stop.fromMap(params1st());
        stop.setInteger("address_id", Integer.parseInt(param("address_id")));
        stop.set("id", Integer.parseInt(param("id")));
        stop.setTime("time",Time.valueOf(param("time")+":00"));
        if(!stop.save()){
            flash("message", "Something went wrong, please restart the process");
            redirect(StopController.class, "stops", stop.get("address_id"));
        }
        else{
            flash("message", "Parada alterada");
            redirect(StopController.class, "stops", stop.get("address_id"));
        }
    }

    @Override @DELETE
    public void delete(){

        Stop stop = Stop.findById(Integer.parseInt(getId()));
        stop.delete();
        flash("message", "Parada foi deletada");
        redirect(StopController.class);
    }

    @Override
    public void show(){
        //this is to protect from URL hacking
        Stop stop = Stop.findById(Integer.parseInt(getId()));
        if(stop != null){
            view("stop", stop);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }
}
