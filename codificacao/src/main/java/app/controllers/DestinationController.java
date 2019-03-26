package app.controllers;

import app.json.DestinationFlowJson;
import app.models.*;

import java.util.List;
import java.util.Map;

public class DestinationController extends GenericAppController {

    //Partir do estado para cidade e depois permitir o cadastro.

    @Override
    public void index(){
        
    }

    public void cities(){
        /*
        List<Map<String, Object>> citiesList = City.find("state_id = ?",
                Integer.parseInt(getId())).toMaps();
                */
        
    }

    public void addresses(){
        /*
        List<Map<String, Object>> addressList = Address.find("city_id = ?",
                Integer.parseInt(getId())).toMaps();
                */
        
    }

    public void destinations(){
        /*
        List<Map<String, Object>> destinationList = Destination.find("address_id = ?",
                Integer.parseInt(getId())).toMaps();
        for (Map<String, Object> stop : destinationList){
            stop.put("address", Address.findById(stop.get("address_id")).toMap());
        }
        */
        
    }

    public void destination(){
        /*
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
        */
    }

    @Override
    public void create(){
        /*
        Destination destination = new Destination();
        destination.fromMap(params1st());
        destination.setInteger("address_id", Integer.parseInt(param("address_id")));
        if(!destination.save()){
            
            
            
            
        }else{
            
            
        }
        */
    }

    @Override
    public void newForm(){
        
    }

    @Override
    public void alterForm(){
        /*
        Destination destination = Destination.findById(Integer.parseInt(getId()));
        if(destination != null){
            
        }else{
            
            
        }
        */
    }

    @Override
    public void update(){
        /*
        Destination destination = new Destination();
        destination.fromMap(params1st());
        destination.setInteger("address_id", Integer.parseInt(param("address_id")));
        destination.set("id", Integer.parseInt(param("id")));
        if(!destination.save()){
            
            
        }
        else{
            
            
        }
        */
    }

    @Override
    public void delete(){
        /*

        Destination destination = Destination.findById(Integer.parseInt(getId()));
        String name = destination.getString("name");
        destination.delete();
        */
        
        
    }
}
