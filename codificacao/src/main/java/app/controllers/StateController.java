package app.controllers;

import app.json.StateJson;
import app.models.State;
import app.utils.Db;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Context;
import io.javalin.Javalin;
import io.javalin.JavalinEvent;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.DB;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StateController extends GenericAppController {

    @Override
    public void getAll(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            LazyList<State> results = State.findAll();
            ArrayList<StateJson> json = new ArrayList<>();

            for (State state : results) {
                json.add(new StateJson(state));
            }
            ctx.result(mapper.writeValueAsString(json));
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    @Override
    public void getOne(@NotNull Context ctx, @NotNull String resourceId){
        try{
            Base.open(Db.getInstance());
            State state = State.findById(Integer.parseInt(resourceId));
            if(state == null){
                ctx.res.setStatus(404);
            }
            else {
                ctx.res.setStatus(200);
                StateJson stateJson = new StateJson(state);
                ctx.result(mapper.writeValueAsString(stateJson));
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }


    @Override
    public void create(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            State state = new State();
            StateJson stateJson = ctx.bodyAsClass(StateJson.class);
            stateJson.setAttributesOfState(state);
            if(state.saveIt()){
                ctx.res.setStatus(200);
            }
            else{
                ctx.res.setStatus(400);
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }

    }


    @Override
    public void update(@NotNull Context ctx, @NotNull String resourceId){
        try {
            Base.open(Db.getInstance());
            State state = new State();
            StateJson stateJson = ctx.bodyAsClass(StateJson.class);
            stateJson.setAttributesOfState(state);
            if(state.save()){
                ctx.res.setStatus(200);
            }
            else{
                ctx.res.setStatus(400);
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }

    }

    @Override
    public void delete(@NotNull Context ctx, @NotNull String resourceId){
        try{
            Base.open(Db.getInstance());
            State state = State.findById(Integer.parseInt(resourceId));
            if(state == null){
                ctx.res.setStatus(404);
            }
            else {
                if (state.delete()) {
                    ctx.res.setStatus(200);
                } else {
                    ctx.res.setStatus(400);
                }
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    /*
    Adicionar triggers para os processos de criacao, alteracao e delecao
     */

}
