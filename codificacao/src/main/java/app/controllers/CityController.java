package app.controllers;

import app.models.City;
import app.models.State;
import org.javalite.activejdbc.LazyList;

import java.util.List;
import java.util.Map;

public class CityController extends GenericAppController {

    @Override
    public void index(){
        
    }

    public void list(){
        /*
        if(xhr()){
            Map<String, String> map = params1st();
            Integer stateId = Integer.parseInt((String) map.keySet().toArray()[0]);
            if(stateId != 0) {
                LazyList<City> citiesList = City.find("state_id = ?", stateId);
                respond(citiesList.toJson(false));
            }
        }
        */
    }

    public void cities(){
        /*
        List<Map<String, Object>> citiesList = City.find("state_id = ?",
                Integer.parseInt(getId())).toMaps();
                */
        
    }

    @Override
    public void create(){
        /*
        City city = new City();
        city.fromMap(params1st());
        city.setInteger("state_id", Integer.parseInt(param("state_id")));
        if(!city.save()){
            
            
            
            
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
        City city = City.findById(Integer.parseInt(getId()));
        if(city != null){
            
        }else{
            
            
        }
        */
    }

    @Override
    public void update(){
        /*
        City city = new City();
        city.fromMap(params1st());
        city.setInteger("state_id", Integer.parseInt(param("state_id")));
        city.set("id", Integer.parseInt(param("id")));
        if(!city.save()){
            
            
        }
        else{
            
            
        }
        */
    }

    @Override
    public void delete(){

        /*
        City city = City.findById(Integer.parseInt(getId()));
        String name = city.getString("name");
        city.delete();
        */
        
        
    }
}
