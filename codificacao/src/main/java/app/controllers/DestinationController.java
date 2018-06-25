package app.controllers;

import app.controllers.authorization.Protected;
import app.json.DestinationFlowJson;
import app.models.*;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

import java.util.List;
import java.util.Map;

@Protected
public class DestinationController extends GenericAppController {

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

    public void destinations(){
        List<Map<String, Object>> destinationList = Destination.find("address_id = ?",
                Integer.parseInt(getId())).toMaps();
        for (Map<String, Object> stop : destinationList){
            stop.put("address", Address.findById(stop.get("address_id")).toMap());
        }
        view("destinations", destinationList, "address", getId());
    }

    public void destination(){
        if (xhr()) {
            Map<String, String> map = params1st();
            Gson g = new Gson();
            JsonParser jsonParser = new JsonParser();
            jsonParser.parse(String.valueOf(map.keySet().toArray()[0])).getAsJsonObject();
            DestinationFlowJson destinationFlowJson = g.fromJson(jsonParser.parse(
                    String.valueOf(map.keySet().toArray()[0])).getAsJsonObject(), DestinationFlowJson.class);

            if (destinationFlowJson.op == 0) {
                respond(City.find("state_id = ?", destinationFlowJson.id).toJson(false)).contentType("application/json").status(200);
            } else {
                respond(DestinationAddress.find("city_id = ?", destinationFlowJson.id).toJson(false)).contentType("application/json").status(200);
            }
        }
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
            redirect(DestinationController.class, "destinations", destination.get("address_id"));
        }
    }

    @Override
    public void newForm(){
        view("address", getId());
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
            redirect(DestinationController.class, "destinations", destination.get("address_id"));
        }
        else{
            flash("message", "Destino alterado " + destination.get("name"));
            redirect(DestinationController.class, "destinations", destination.get("address_id"));
        }
    }

    @Override @DELETE
    public void delete(){

        Destination destination = Destination.findById(Integer.parseInt(getId()));
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
