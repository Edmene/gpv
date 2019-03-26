package app.controllers;

import app.controllers.authorization.ProtectedAdministrative;
import app.models.State;

@ProtectedAdministrative
public class StateController extends GenericAppController {

    @Override
    public void index(){
        
    }

    @Override
    public void create(){
        /*
        State state = new State();
        state.fromMap(params1st());
        if(!state.save()){
            
            
            
            
        }else{
            
            
        }
        */
    }

    @Override
    public void alterForm(){
        /*
        State state = State.findById(Integer.parseInt(getId()));
        if(state != null){
            
        }else{
            
            
        }
        */
    }

    @Override
    public void update(){
        /*
        State state = new State();
        state.fromMap(params1st());
        state.set("id", Integer.parseInt(param("id")));
        if(!state.save()){
            
            
        }
        else{
            
            
        }
        */
    }

    @Override
    public void delete(){
        /*

        State state = State.findById(Integer.parseInt(getId()));
        String name = state.getString("name");
        state.delete();

        */
    }

}
