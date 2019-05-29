package app.controllers;

import app.json.DriverVehicleReplacementJson;
import app.models.DriverVehicleReplacement;
import app.utils.Db;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DriverVehicleReplacementController extends GenericAppController {

    private ArrayList<DriverVehicleReplacementJson>
    driverVehicleReplacementsToDriverVehicleReplacementJsonList(LazyList<DriverVehicleReplacement> driverVehicleReplacements){
        ArrayList<DriverVehicleReplacementJson> json = new ArrayList<>();
        for (DriverVehicleReplacement driverVehicleReplacement : driverVehicleReplacements) {
            json.add(new DriverVehicleReplacementJson(driverVehicleReplacement));
        }
        return json;
    }

    @Override
    public void getAll(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            LazyList<DriverVehicleReplacement> results = DriverVehicleReplacement.findAll();
            ctx.result(mapper.writeValueAsString(driverVehicleReplacementsToDriverVehicleReplacementJsonList(results)));
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
            DriverVehicleReplacement driverVehicleReplacement = DriverVehicleReplacement.findById(Integer.parseInt(resourceId));
            DriverVehicleReplacementJson stateJson = new DriverVehicleReplacementJson(driverVehicleReplacement);
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
            DriverVehicleReplacement driverVehicleReplacement = new DriverVehicleReplacement();
            DriverVehicleReplacementJson driverVehicleReplacementJson  = ctx.bodyAsClass(DriverVehicleReplacementJson.class);
            driverVehicleReplacementJson.setAttributesOfDriverVehicleReplacement(driverVehicleReplacement);
            if(driverVehicleReplacement.saveIt()){
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
            DriverVehicleReplacement driverVehicleReplacement = new DriverVehicleReplacement();
            DriverVehicleReplacementJson driverVehicleReplacementJson = ctx.bodyAsClass(DriverVehicleReplacementJson.class);
            driverVehicleReplacementJson.setAttributesOfDriverVehicleReplacement(driverVehicleReplacement);
            if(driverVehicleReplacement.save()){
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
            DriverVehicleReplacement driverVehicleReplacement = DriverVehicleReplacement.findById(Integer.parseInt(resourceId));
            if(driverVehicleReplacement.delete()){
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
