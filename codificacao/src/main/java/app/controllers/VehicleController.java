package app.controllers;

import app.controllers.authorization.ProtectedAdministrative;
import app.models.Vehicle;
import app.utils.TransformMaskeredInput;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

@ProtectedAdministrative
public class VehicleController extends GenericAppController {

    @Override
    public void index(){
        
    }

    @Override @POST
    public void create(){
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
    }

    @Override @DELETE
    public void delete(){

        Vehicle vehicle = Vehicle.findById(Integer.parseInt(getId()));
        String placa = vehicle.getString("license_plate");
        vehicle.delete();
        
        
    }

    @Override @PUT
    public void alterForm(){
        Vehicle vehicle = Vehicle.findById(Integer.parseInt(getId()));
        if(vehicle != null){
            
        }else{
            
            
        }
    }

    @Override @POST
    public void update(){
        Vehicle vehicle = new Vehicle();
        vehicle.fromMap(params1st());
        vehicle.set("id", Integer.parseInt(param("id")));
        vehicle.set("license_plate", TransformMaskeredInput.format(param("license_plate")));
        if(!vehicle.save()){
            
            
        }
        else{
            
            
        }
    }
}
