package app.controllers;

import app.models.Address;
import app.models.City;
import app.models.State;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

import java.util.List;
import java.util.Map;

public class AddressController extends GenericAppController {

    //Partir do estado para cidade e depois permitir o cadastro.


    @Override
    public void index(){
        view("states", State.findAll());
    }

    public void cities(){
        List<Map<String, Object>> citiesList = City.find("state_id = ?",
                Integer.parseInt(getId())).toMaps();
        view("cities", citiesList);
    }

    public void addresses(){

        List<Map<String, Object>> addressList = Address.find("city_id = ?",
                Integer.parseInt(getId())).toMaps();
        view("addresses", addressList, "city", City.findById(Integer.parseInt(getId())));
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
            redirect(AddressController.class, "addresses", address.get("city_id"));
        }
    }

    @Override
    public void newForm(){
        view("city", getId());
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
            redirect(AddressController.class, "addresses", address.get("city_id"));
        }
        else{
            flash("message", "Endereco alterado " + address.get("name"));
            redirect(AddressController.class, "addresses", address.get("city_id"));
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
