package app.controllers;

import app.models.Holiday;

public class HolidayController extends GenericAppController {

    @Override
    public void index(){
        
    }

    @Override
    public void create(){
        /*
        Holiday holiday = new Holiday();
        holiday.fromMap(params1st());
        holiday.setShort("day", Short.parseShort(param("day")));
        holiday.setShort("month", Short.parseShort(param("month")));
        if(!holiday.save()){
            
            
            
            
        }else{
            
            
        }
        */
    }

    @Override
    public void delete(){
        /*

        Holiday holiday = Holiday.findById(Integer.parseInt(getId()));
        String name = holiday.getString("name");
        holiday.delete();

        */
        
        
    }

    @Override
    public void alterForm(){
        /*
        Holiday holiday = Holiday.findById(Integer.parseInt(getId()));
        if(holiday != null){
            
        }else{
            
            
        }
        */
    }

    @Override
    public void update(){
        /*
        Holiday holiday = new Holiday();
        holiday.fromMap(params1st());
        holiday.set("id", Integer.parseInt(param("id")));
        if(!holiday.save()){
            
            
        }
        else{
            
            
        }
        */
    }
}
