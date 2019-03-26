package app.controllers;

import app.controllers.authorization.ProtectedAdministrative;
import app.models.Holiday;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

@ProtectedAdministrative
public class HolidayController extends GenericAppController {

    @Override
    public void index(){
        
    }

    @Override @POST
    public void create(){
        Holiday holiday = new Holiday();
        holiday.fromMap(params1st());
        holiday.setShort("day", Short.parseShort(param("day")));
        holiday.setShort("month", Short.parseShort(param("month")));
        if(!holiday.save()){
            
            
            
            
        }else{
            
            
        }
    }

    @Override @DELETE
    public void delete(){

        Holiday holiday = Holiday.findById(Integer.parseInt(getId()));
        String name = holiday.getString("name");
        holiday.delete();
        
        
    }

    @Override @PUT
    public void alterForm(){
        Holiday holiday = Holiday.findById(Integer.parseInt(getId()));
        if(holiday != null){
            
        }else{
            
            
        }
    }

    @Override @POST
    public void update(){
        Holiday holiday = new Holiday();
        holiday.fromMap(params1st());
        holiday.set("id", Integer.parseInt(param("id")));
        if(!holiday.save()){
            
            
        }
        else{
            
            
        }
    }
}
