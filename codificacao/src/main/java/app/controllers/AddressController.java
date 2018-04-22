package app.controllers;

import app.models.City;
import app.models.Address;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

public class AddressController extends AppController {
    public void index(){
        view("addresses", Address.findAll().toMaps());
    }

    @POST
    public void create(){
        Address address = new Address();
        address.fromMap(params1st());
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

    @PUT
    public void alterForm(){
        Address address = Address.findById(Integer.parseInt(getId()));
        if(address != null){
            view("address", address, "cities", City.findAll().toMaps());
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @POST
    public void update(){
        Address address = new Address();
        address.fromMap(params1st());
        address.setInteger("city_id", Integer.parseInt(param("city_id")));
        address.set("id", Integer.parseInt(param("id")));
        if(!address.save()){
            flash("message", "Something went wrong, please restart the process");
            redirect(AddressController.class);
        }
        else{
            flash("message", "Endereco alterado " + address.get("name"));
            redirect(AddressController.class);
        }
    }

    @DELETE
    public void delete(){

        Address address = Address.findById(Integer.parseInt(getId()));
        //Integer id = Integer.valueOf(getId());
        //Driver address = (Driver) Driver.findBySQL("SELECT * FROM DRIVERS WHERE id = ?",id).get(0);

        String name = address.getString("name");
        address.delete();
        flash("message", "Endereco: '" + name + "' was deleted");
        redirect(AddressController.class);
    }

    public void show(){
        //this is to protect from URL hacking
        Address address = Address.findById(Integer.parseInt(getId()));
        if(address != null){
            view("address", address);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }
}
