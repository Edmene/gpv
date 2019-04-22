package app.controllers;

import app.json.DestinationJson;
import app.models.*;
import app.utils.Db;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DestinationController extends GenericAppController {

    //Partir do estado para cidade e depois permitir o cadastro.

    private ArrayList<DestinationJson> destinationsToDestinationJsonList(LazyList<Destination> destinations){
        ArrayList<DestinationJson> json = new ArrayList<>();
        for (Destination destination : destinations) {
            json.add(new DestinationJson(destination));
        }
        return json;
    }

    @Override
    public void getAll(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            LazyList<Destination> results = Destination.findAll();
            ctx.result(mapper.writeValueAsString(destinationsToDestinationJsonList(results)));
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    public void cities(){
        /*
        List<Map<String, Object>> citiesList = City.find("state_id = ?",
                Integer.parseInt(getId())).toMaps();
                */
        
    }

    public void addresses(){
        /*
        List<Map<String, Object>> addressList = Road.find("city_id = ?",
                Integer.parseInt(getId())).toMaps();
                */
        
    }

    public void destinations(){
        /*
        List<Map<String, Object>> destinationList = Destination.find("address_id = ?",
                Integer.parseInt(getId())).toMaps();
        for (Map<String, Object> stop : destinationList){
            stop.put("address", Road.findById(stop.get("address_id")).toMap());
        }
        */

    }


    @Override
    public void getOne(@NotNull Context ctx, @NotNull String resourceId){
        try{
            Base.open(Db.getInstance());
            Destination city = Destination.findById(Integer.parseInt(resourceId));
            DestinationJson stateJson = new DestinationJson(city);
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
            Destination destination = new Destination();
            DestinationJson destinationJson  = ctx.bodyAsClass(DestinationJson.class);
            destinationJson.setAttributesOfDestination(destination);
            if(destination.saveIt()){
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
    public void update(@NotNull Context ctx, @NotNull String contentId){
        try {
            Base.open(Db.getInstance());
            Destination destination = new Destination();
            DestinationJson destinationJson = ctx.bodyAsClass(DestinationJson.class);
            destinationJson.setAttributesOfDestination(destination);
            if(destination.save()){
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
            Destination destination = Destination.findById(Integer.parseInt(resourceId));
            if(destination.delete()){
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
