package app.controllers;

import app.models.Address;
import app.models.City;
import app.models.State;

import java.util.List;
import java.util.Map;

public class AddressController extends GenericAppController {

    //Partir do estado para cidade e depois permitir o cadastro.


    @Override
    public void index(){
        
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
    public void create(){
        Address address = new Address();
        //address.fromMap();
        //address.setInteger("city_id", Integer.parseInt());
        if(!address.save()){
            
            
            
            
        }else{
            
            
        }
    }

    @Override
    public void newForm(){
        
    }

    @Override
    public void alterForm(){
        /*Address address = Address.findById(Integer.parseInt());
        if(address != null){
            
        }else{
            
            
        }
        */
    }

    @Override
    public void update(){
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
    public void delete(){

        //Address address = Address.findById(Integer.parseInt());
        //String name = address.getString("name");
        //address.delete();
        
        
    }
}
