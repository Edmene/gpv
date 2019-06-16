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

    public void getByCity(@NotNull Context ctx, @NotNull String resourceId){
        try{
            Base.open(Db.getInstance());
            LazyList<DestinationRoad> destinations = DestinationRoad.find("city_id = ?", Integer.parseInt(resourceId));
            ctx.result((destinations.toJson(false)));
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
            Destination destination = Destination.findById(Integer.parseInt(resourceId));
            if(destination == null){
                ctx.res.setStatus(404);
            }
            else {
                ctx.res.setStatus(200);
                DestinationJson destinationJson = new DestinationJson(destination);
                ctx.result(mapper.writeValueAsString(destinationJson));
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
            Destination destination = Destination.findById(Integer.parseInt(contentId));
            DestinationJson destinationJson = ctx.bodyAsClass(DestinationJson.class);
            if(destination == null) {
                ctx.res.setStatus(404);
                return;
            }
            if(destination.getId() != destinationJson.key){
                ctx.res.setStatus(400);
                return;
            }
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
            if(destination == null){
                ctx.res.setStatus(404);
            }
            else {
                if (destination.delete()) {
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
