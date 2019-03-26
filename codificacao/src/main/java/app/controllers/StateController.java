package app.controllers;

import app.controllers.authorization.ProtectedAdministrative;
import app.models.State;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

@ProtectedAdministrative
public class StateController extends GenericAppController {

    @Override
    public void index(){
        
    }

    @Override @POST
    public void create(){
        State state = new State();
        state.fromMap(params1st());
        if(!state.save()){
            
            
            
            
        }else{
            
            
        }
    }

    @Override @PUT
    public void alterForm(){
        State state = State.findById(Integer.parseInt(getId()));
        if(state != null){
            
        }else{
            
            
        }
    }

    @Override @POST
    public void update(){
        State state = new State();
        state.fromMap(params1st());
        state.set("id", Integer.parseInt(param("id")));
        if(!state.save()){
            
            
        }
        else{
            
            
        }
    }

    @Override @DELETE
    public void delete(){

        State state = State.findById(Integer.parseInt(getId()));
        String name = state.getString("name");
        state.delete();
        
        
    }

}
