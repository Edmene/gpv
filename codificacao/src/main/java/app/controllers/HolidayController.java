package app.controllers;

import app.models.Holiday;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;

public class HolidayController extends GenericAppController {

    @Override
    public void getAll(@NotNull Context ctx){
        
    }

    @Override
    public void create(@NotNull Context ctx){
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
    public void delete(@NotNull Context ctx, @NotNull String contentId){
        /*

        Holiday holiday = Holiday.findById(Integer.parseInt(getId()));
        String name = holiday.getString("name");
        holiday.delete();

        */
        
        
    }

    @Override
    public void update(@NotNull Context ctx, @NotNull String contentId){
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
