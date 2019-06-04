package app.controllers;

import app.controllers.authorization.ProtectedAdministrative;
import app.json.VehicleJson;
import app.models.Vehicle;
import app.utils.Db;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@ProtectedAdministrative
public class VehicleController extends GenericAppController {

    private ArrayList<VehicleJson> vehiclesToVehicleJsonList(LazyList<Vehicle> vehicles){
        ArrayList<VehicleJson> json = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            json.add(new VehicleJson(vehicle));
        }
        return json;
    }

    @Override
    public void getAll(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            LazyList<Vehicle> results = Vehicle.findAll();
            ctx.result(mapper.writeValueAsString(vehiclesToVehicleJsonList(results)));
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
            Vehicle vehicle = Vehicle.findById(Integer.parseInt(resourceId));
            if(vehicle == null){
                ctx.res.setStatus(404);
            }
            else {
                ctx.res.setStatus(200);
                VehicleJson vehicleJson = new VehicleJson(vehicle);
                ctx.result(mapper.writeValueAsString(vehicleJson));
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
            Vehicle vehicle = new Vehicle();
            VehicleJson vehicleJson  = ctx.bodyAsClass(VehicleJson.class);
            vehicleJson.setAttributesOfVehicle(vehicle);
            if(vehicle.saveIt()){
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
            Vehicle vehicle = Vehicle.findById(Integer.parseInt(resourceId));
            if(vehicle == null){
                ctx.res.setStatus(404);
            }
            else {
                if (vehicle.delete()) {
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


    @Override
    public void update(@NotNull Context ctx, @NotNull String resourceId){
        try {
            Base.open(Db.getInstance());
            Vehicle vehicle = Vehicle.findById(Integer.parseInt(resourceId));
            VehicleJson vehicleJson = ctx.bodyAsClass(VehicleJson.class);
            if(vehicle == null) {
                ctx.res.setStatus(404);
                return;
            }
            if(vehicle.getId() != vehicleJson.key){
                ctx.res.setStatus(400);
                return;
            }
            vehicleJson.setAttributesOfVehicle(vehicle);
            if(vehicle.save()){
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
