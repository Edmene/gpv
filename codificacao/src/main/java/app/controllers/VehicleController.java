package app.controllers;

import app.controllers.authorization.ProtectedAdministrative;
import app.models.Vehicle;
import app.utils.TransformMaskeredInput;
import io.javalin.Context;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

@ProtectedAdministrative
public class VehicleController extends GenericAppController {

    @Override
    public void getAll(@NotNull Context ctx){
        
    }

    @Override
    public void create(@NotNull Context ctx){
        /*
        Vehicle vehicle = new Vehicle();
        vehicle.fromMap(params1st());
        vehicle.setShort("capacity", Short.parseShort(param("capacity")));
        vehicle.setShort("year", Short.parseShort(param("year")));
        vehicle.set("license_plate", TransformMaskeredInput.format(param("license_plate")));
        if(!vehicle.save()){
            
            
            
            
        }else{
            LazyList a = Vehicle.find("capacity = ? AND license_plate = ? AND model = ? AND year = ?",
                    vehicle.get("capacity"), vehicle.get("license_plate"), vehicle.get("model"), vehicle.get("year"));
            
            
        }
        */
    }

    @Override
    public void delete(@NotNull Context ctx, @NotNull String contentId){
        /*
        Vehicle vehicle = Vehicle.findById(Integer.parseInt(getId()));
        String placa = vehicle.getString("license_plate");
        vehicle.delete();
        */
        
    }


    @Override
    public void update(@NotNull Context ctx, @NotNull String contentId){
        /*
        Vehicle vehicle = new Vehicle();
        vehicle.fromMap(params1st());
        vehicle.set("id", Integer.parseInt(param("id")));
        vehicle.set("license_plate", TransformMaskeredInput.format(param("license_plate")));
        if(!vehicle.save()){
            
            
        }
        else{
            
            
        }
        */
    }
}
