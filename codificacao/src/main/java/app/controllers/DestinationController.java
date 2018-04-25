package app.controllers;

import app.models.Address;
import app.models.Destination;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

public class DestinationController extends GenericAppController {

    @Override
    public void index(){
        view("destinations", Destination.findAll().toMaps());
    }

    @Override @POST
    public void create(){
        Destination destination = new Destination();
        destination.fromMap(params1st());
        destination.setInteger("address_id", Integer.parseInt(param("address_id")));
        if(!destination.save()){
            flash("message", "Something went wrong, please  fill out all fields");
            flash("errors", destination.errors());
            flash("params", params1st());
            redirect(DestinationController.class, "new_form");
        }else{
            flash("message", "Novo destino cadastrado: " + destination.get("name"));
            redirect(DestinationController.class);
        }
    }

    @Override
    public void newForm(){
        view("addresses", Address.findAll().toMaps());
    }

    @Override @PUT
    public void alterForm(){
        Destination destination = Destination.findById(Integer.parseInt(getId()));
        if(destination != null){
            view("destination", destination, "addresses", Address.findAll().toMaps());
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override @POST
    public void update(){
        Destination destination = new Destination();
        destination.fromMap(params1st());
        destination.setInteger("address_id", Integer.parseInt(param("address_id")));
        destination.set("id", Integer.parseInt(param("id")));
        if(!destination.save()){
            flash("message", "Something went wrong, please restart the process");
            redirect(DestinationController.class);
        }
        else{
            flash("message", "Destino alterado " + destination.get("name"));
            redirect(DestinationController.class);
        }
    }

    @Override @DELETE
    public void delete(){

        Destination destination = Destination.findById(Integer.parseInt(getId()));
        //Integer id = Integer.valueOf(getId());
        //Driver destination = (Driver) Driver.findBySQL("SELECT * FROM DRIVERS WHERE id = ?",id).get(0);

        String name = destination.getString("name");
        destination.delete();
        flash("message", "Destino: '" + name + "' was deleted");
        redirect(DestinationController.class);
    }

    @Override
    public void show(){
        //this is to protect from URL hacking
        Destination destination = Destination.findById(Integer.parseInt(getId()));
        if(destination != null){
            view("destination", destination);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }
}
