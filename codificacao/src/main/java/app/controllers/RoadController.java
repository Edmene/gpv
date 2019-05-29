package app.controllers;

import app.json.RoadJson;
import app.models.Road;
import app.utils.Db;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RoadController extends GenericAppController {

    private ArrayList<RoadJson> citiesToRoadJsonList(LazyList<Road> cities){
        ArrayList<RoadJson> json = new ArrayList<>();
        for (Road city : cities) {
            json.add(new RoadJson(city));
        }
        return json;
    }


    @Override
    public void getAll(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            LazyList<Road> results = Road.findAll();
            ctx.result(mapper.writeValueAsString(citiesToRoadJsonList(results)));
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
            Road road = Road.findById(Integer.parseInt(resourceId));
            RoadJson stateJson = new RoadJson(road);
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
            Road road = new Road();
            RoadJson roadJson  = ctx.bodyAsClass(RoadJson.class);
            roadJson.setAttributesOfRoad(road);
            if(road.saveIt()){
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
            Road road = new Road();
            RoadJson roadJson = ctx.bodyAsClass(RoadJson.class);
            roadJson.setAttributesOfRoad(road);
            if(road.save()){
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
            Road road = Road.findById(Integer.parseInt(resourceId));
            if(road.delete()){
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

    /*
    Adicionar triggers para os processos de criacao, alteracao e delecao
     */
}
