package app.controllers;

import app.controllers.authorization.ProtectedAdministrative;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;

@ProtectedAdministrative
public class StopController extends GenericAppController{

    //Partir do estado para cidade e depois permitir o cadastro.

    @Override
    public void getAll(@NotNull Context ctx){
        
    }

    public void cities(){
        /*
        List<Map<String, Object>> citiesList = City.find("state_id = ?",
                Integer.parseInt(getId())).toMaps();
                */
        
    }

    public void addresses(){
        /*
        List<Map<String, Object>> addressList = Road.find("city_id = ?",
                Integer.parseInt(getId())).toMaps();
                */
        
    }

    public void stops(){
        /*
        List<Map<String, Object>> stopList = Stop.find("address_id = ?",
                Integer.parseInt(getId())).toMaps();
        for (Map<String, Object> stop : stopList){
            stop.put("address", Road.findById(stop.get("address_id")).toMap());
        }
        */
    }

    @Override
    public void create(@NotNull Context ctx){
        /*
        Stop stop = new Stop();
        stop.fromMap(params1st());
        stop.setInteger("address_id", Integer.parseInt(param("address_id")));
        stop.setTime("time",Time.valueOf(param("time")+":00"));
        if(!stop.save()){
            
            
            
            
        }else{
            
            
        }
        */
    }

    @Override
    public void update(@NotNull Context ctx, @NotNull String contentId){
        /*
        Stop stop = new Stop();
        stop.fromMap(params1st());
        stop.setInteger("address_id", Integer.parseInt(param("address_id")));
        stop.set("id", Integer.parseInt(param("id")));
        stop.setTime("time",Time.valueOf(param("time")+":00"));
        if(!stop.save()){
            
            
        }
        else{
            
            
        }
        */
    }

    @Override
    public void delete(@NotNull Context ctx, @NotNull String contentId){
        /*
        Stop stop = Stop.findById(Integer.parseInt(getId()));
        stop.delete();
        */
    }
}
