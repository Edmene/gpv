package app.controllers;

import app.json.DriverVehicleJson;
import app.models.DriverVehicle;
import app.utils.Db;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DriverVehicleController extends GenericAppController {

    private ArrayList<DriverVehicleJson> driverVehiclesToDriverVehicleJsonList(LazyList<DriverVehicle> driverVehicles){
        ArrayList<DriverVehicleJson> json = new ArrayList<>();
        for (DriverVehicle driverVehicle : driverVehicles) {
            json.add(new DriverVehicleJson(driverVehicle));
        }
        return json;
    }

    @Override
    public void getAll(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            LazyList<DriverVehicle> results = DriverVehicle.findAll();
            ctx.result(mapper.writeValueAsString(driverVehiclesToDriverVehicleJsonList(results)));
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    public void getOne(@NotNull Context ctx, @NotNull String resourceId, @NotNull String vehicleId){
        try{
            Base.open(Db.getInstance());
            DriverVehicle driverVehicle = DriverVehicle.findByCompositeKeys(Integer.parseInt(resourceId),
                    Integer.parseInt(vehicleId));
            if(driverVehicle == null){
                ctx.res.setStatus(404);
            }
            else {
                ctx.res.setStatus(200);
                DriverVehicleJson driverVehicleJson = new DriverVehicleJson(driverVehicle);
                ctx.result(mapper.writeValueAsString(driverVehicleJson));
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
            DriverVehicle driverVehicle = new DriverVehicle();
            DriverVehicleJson driverVehicleJson  = ctx.bodyAsClass(DriverVehicleJson.class);
            driverVehicleJson.setAttributesOfDriverVehicle(driverVehicle);
            if(driverVehicle.saveIt()){
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

    public void update(@NotNull Context ctx, @NotNull String resourceId, @NotNull String vehicleId){
        try {
            Base.open(Db.getInstance());
            DriverVehicle driverVehicle = DriverVehicle.findByCompositeKeys(Integer.parseInt(resourceId),
                    Integer.parseInt(vehicleId));
            DriverVehicleJson driverVehicleJson = ctx.bodyAsClass(DriverVehicleJson.class);
            if(driverVehicle == null){
                ctx.res.setStatus(404);
                return;
            }
            driverVehicleJson.setAttributesOfDriverVehicle(driverVehicle);
            if(driverVehicle.save()){
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

    public void delete(@NotNull Context ctx, @NotNull String resourceId, @NotNull String vehicleId){
        try {
            Base.open(Db.getInstance());
            DriverVehicle driverVehicle = DriverVehicle.findByCompositeKeys(Integer.parseInt(resourceId),
                    Integer.parseInt(vehicleId));
            if(driverVehicle == null){
                ctx.res.setStatus(404);
            }
            else {
                if (driverVehicle.delete()) {
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
