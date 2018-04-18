package app.controllers;

import app.models.State;
import app.models.Stop;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

public class StateController extends AppController {
    public void index(){
        view("states", State.findAll().toMaps());
    }

    @POST
    public void create(){
        State state = new State();
        state.fromMap(params1st());
        if(!state.save()){
            flash("message", "Something went wrong, please  fill out all fields");
            flash("errors", state.errors());
            flash("params", params1st());
            redirect(StateController.class, "new_form");
        }else{
            flash("message", "New book was added: " + state.get("name"));
            redirect(StateController.class);
        }
    }

    public void newForm(){}

    @PUT
    public void alterForm(){
        State state = State.findById(Integer.parseInt(getId()));
        if(state != null){
            view("state", state);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @POST
    public void update(){
        State state = new State();
        state.fromMap(params1st());
        state.set("id", Integer.parseInt(param("id")));
        if(!state.save()){
            flash("message", "Something went wrong, please restart the process");
            redirect(StateController.class);
        }
        else{
            flash("message", "Estado alterado " + state.get("name"));
            redirect(StateController.class);
        }
    }

    @DELETE
    public void delete(){

        Stop stop = Stop.findById(Integer.parseInt(getId()));
        //Integer id = Integer.valueOf(getId());
        //Driver stop = (Driver) Driver.findBySQL("SELECT * FROM DRIVERS WHERE id = ?",id).get(0);

        String name = stop.getString("name");
        stop.delete();
        flash("message", "Estado: '" + name + "' was deleted");
        redirect(StateController.class);
    }

    public void show(){
        //this is to protect from URL hacking
        State state = State.findById(Integer.parseInt(getId()));
        if(state != null){
            view("state", state);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }
}
