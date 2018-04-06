package app.controllers;

import app.models.City;
import app.models.Address;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.POST;

public class AddressController extends AppController {
    public void index(){
        view("addresses", Address.findAll().toMaps());
    }

    @POST
    public void create(){
        Address address = new Address();
        //address.fromMap(params1st());
        //String name = param("name");
        address.set("name", param("name"));
        address.set("extra", param("extra"));
        address.setInteger("city_id", Integer.parseInt(param("city_id")));
        if(!address.save()){
            flash("message", "Something went wrong, please  fill out all fields");
            flash("errors", address.errors());
            flash("params", params1st());
            redirect(AddressController.class, "new_form");
        }else{
            flash("message", "Novo endereco cadastrado: " + address.get("name"));
            redirect(AddressController.class);
        }
    }

    public void newForm(){
        view("cities", City.findAll().toMaps());
    }
}
