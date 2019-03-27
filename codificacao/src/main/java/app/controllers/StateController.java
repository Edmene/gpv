package app.controllers;

import app.models.State;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.jetbrains.annotations.NotNull;

public class StateController extends GenericAppController {

    @Override
    public void getAll(@NotNull Context ctx){
        String results = State.findAll().toString();
        ctx.result(results);
        Base.close();
    }

    @Override
    public void getOne(@NotNull Context ctx, @NotNull String resourceId){

    }


    @Override
    public void create(@NotNull Context ctx){
        /*
        State state = new State();
        state.fromMap(params1st());
        if(!state.save()){
            
            
            
            
        }else{
            
            
        }
        */
    }


    @Override
    public void update(@NotNull Context ctx, @NotNull String resourceId){
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
    public void delete(@NotNull Context ctx, @NotNull String resourceId){
        /*

        State state = State.findById(Integer.parseInt(getId()));
        String name = state.getString("name");
        state.delete();

        */
    }

}
