package app.controllers;

import app.models.State;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.POST;

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
}
