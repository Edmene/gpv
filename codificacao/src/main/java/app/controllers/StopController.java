package app.controllers;

import app.json.StopJson;
import app.models.Stop;
import app.utils.Db;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StopController extends GenericAppController {

    private ArrayList<StopJson> citiesToStopJsonList(LazyList<Stop> cities){
        ArrayList<StopJson> json = new ArrayList<>();
        for (Stop stop : cities) {
            json.add(new StopJson(stop));
        }
        return json;
    }

    @Override
    public void getAll(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            LazyList<Stop> results = Stop.findAll();
            ctx.result(mapper.writeValueAsString(citiesToStopJsonList(results)));
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
            Stop stop = Stop.findById(Integer.parseInt(resourceId));
            StopJson stateJson = new StopJson(stop);
            ctx.result(mapper.writeValueAsString(stateJson));
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
            Stop stop = new Stop();
            StopJson stopJson  = ctx.bodyAsClass(StopJson.class);
            stopJson.setAttributesOfStop(stop);
            if(stop.saveIt()){
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
            Stop stop = new Stop();
            StopJson stopJson = ctx.bodyAsClass(StopJson.class);
            stopJson.setAttributesOfStop(stop);
            if(stop.save()){
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
            Stop stop = Stop.findById(Integer.parseInt(resourceId));
            if(stop.delete()){
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
}
