package app.controllers;

import app.models.Vehicle;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

public class VehicleController extends GenericAppController {

    @Override
    public void index(){
        view("vehicles", Vehicle.findAll().toMaps());
    }

    @Override @POST
    public void create(){
        Vehicle vehicle = new Vehicle();
        vehicle.fromMap(params1st());
        vehicle.setShort("capacity", Short.parseShort(param("capacity")));
        vehicle.setShort("year", Short.parseShort(param("year")));
        if(!vehicle.save()){
            flash("message", "Something went wrong, please  fill out all fields");
            flash("errors", vehicle.errors());
            flash("params", params1st());
            redirect(VehicleController.class, "new_form");
        }else{
            LazyList a = Vehicle.find("capacity = ? AND license_plate = ? AND model = ? AND year = ?",
                    vehicle.get("capacity"), vehicle.get("license_plate"), vehicle.get("model"), vehicle.get("year"));
            flash("message", "New vehicle was added: " + vehicle.get("license_plate") + " " + a.collect("id").get(0));
            redirect(VehicleController.class);
        }
    }

    @Override
    public void show(){
        //this is to protect from URL hacking
        Vehicle vehicle = Vehicle.findById(Integer.parseInt(getId()));
        if(vehicle != null){
            view("vehicle", vehicle);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override @DELETE
    public void delete(){

        Vehicle vehicle = Vehicle.findById(Integer.parseInt(getId()));
        //Integer id = Integer.valueOf(getId());
        //Driver vehicle = (Driver) Driver.findBySQL("SELECT * FROM DRIVERS WHERE id = ?",id).get(0);

        String placa = vehicle.getString("license_plate");
        vehicle.delete();
        flash("message", "Veiculo: '" + placa + "' was deleted");
        redirect(VehicleController.class);
    }

    @Override @PUT
    public void alterForm(){
        Vehicle vehicle = Vehicle.findById(Integer.parseInt(getId()));
        if(vehicle != null){
            view("vehicle", vehicle);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override @POST
    public void update(){
        Vehicle vehicle = new Vehicle();
        vehicle.fromMap(params1st());
        vehicle.set("id", Integer.parseInt(param("id")));
        if(!vehicle.save()){
            flash("message", "Something went wrong, please restart the process");
            redirect(VehicleController.class);
        }
        else{
            flash("message", "Veiculo alterado " + vehicle.get("placa"));
            redirect(VehicleController.class);
        }
    }
}
