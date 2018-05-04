package app.controllers;

import app.models.Address;
import app.models.City;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

import java.util.List;
import java.util.Map;

public class AddressController extends GenericAppController {

    @Override
    public void index(){
        List<Map<String, Object>> addressList = Address.findAll().toMaps();
        for (Map<String, Object> address : addressList){
            address.put("city", City.findById(address.get("city_id")).toMap());
        }
        view("addresses", addressList);
    }

    @Override @POST
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

    @Override
    public void newForm(){
        view("cities", City.findAll().toMaps());
    }

    @Override @PUT
    public void alterForm(){
        Address address = Address.findById(Integer.parseInt(getId()));
        if(address != null){
            view("address", address, "cities", City.findAll().toMaps());
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override @POST
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

    @Override @DELETE
    public void delete(){

        Address address = Address.findById(Integer.parseInt(getId()));
        String name = address.getString("name");
        address.delete();
        flash("message", "Endereco: '" + name + "' was deleted");
        redirect(AddressController.class);
    }

    @Override
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
