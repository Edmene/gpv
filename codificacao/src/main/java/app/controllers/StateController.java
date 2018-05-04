package app.controllers;

import app.models.State;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

public class StateController extends GenericAppController {

    @Override
    public void index(){
        view("states", State.findAll().toMaps());
    }

    @Override @POST
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

    @Override @PUT
    public void alterForm(){
        State state = State.findById(Integer.parseInt(getId()));
        if(state != null){
            view("state", state);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override @POST
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

    @Override @DELETE
    public void delete(){

        State state = State.findById(Integer.parseInt(getId()));
        String name = state.getString("name");
        state.delete();
        flash("message", "Estado: '" + name + "' was deleted");
        redirect(StateController.class);
    }

    @Override
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
