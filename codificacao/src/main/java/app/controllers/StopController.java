package app.controllers;

import app.models.Address;
import app.models.Stop;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class StopController extends GenericAppController{

    //Partir do estado para cidade e depois permitir o cadastro.

    @Override
    public void index(){
        List<Map<String, Object>> stopList = Stop.findAll().toMaps();
        for (Map<String, Object> stop : stopList){
            stop.put("address", Address.findById(stop.get("address_id")).toMap());
        }
        view("stops", stopList);
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
            redirect(StopController.class);
        }
    }

    @Override
    public void newForm(){
        view("addresses", Address.findAll().toMaps());
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
            redirect(StopController.class);
        }
        else{
            flash("message", "Parada alterada");
            redirect(StopController.class);
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
