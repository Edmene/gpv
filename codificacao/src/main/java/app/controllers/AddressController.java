package app.controllers;

import app.models.Address;
import app.models.City;
import app.models.State;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class AddressController extends GenericAppController {

    //Partir do estado para cidade e depois permitir o cadastro.


    @Override
    public void getAll(@NotNull Context ctx){
        
    }

    public void cities(){
        /*
        List<Map<String, Object>> citiesList = City.find("state_id = ?",
                Integer.parseInt()).toMaps();
                */
        
    }

    public void addresses(){
        /*
        List<Map<String, Object>> addressList = Address.find("city_id = ?",
                Integer.parseInt()).toMaps();
                */
        
    }

    @Override
    public void create(@NotNull Context ctx){
        Address address = new Address();
        //address.fromMap();
        //address.setInteger("city_id", Integer.parseInt());
        if(!address.save()){
            
            
            
            
        }else{
            
            
        }
    }

    @Override
    public void update(@NotNull Context ctx, @NotNull String contentId){
        Address address = new Address();
        //address.fromMap();
        //address.setInteger("city_id", Integer.parseInt());
        //address.set("id", Integer.parseInt());
        if(!address.save()){
            
            
        }
        else{
            
            
        }
    }

    @Override
    public void delete(@NotNull Context ctx, @NotNull String contentId){

        //Address address = Address.findById(Integer.parseInt());
        //String name = address.getString("name");
        //address.delete();
        
        
    }
}
