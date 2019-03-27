package app.controllers;

import app.models.Driver;
import app.utils.TransformMaskeredInput;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;

public class DriverController extends GenericAppController {

    @Override
    public void getAll(@NotNull Context ctx){
        
    }

    @Override
    public void create(@NotNull Context ctx){
        /*
        Driver driver = new Driver();
        driver.fromMap(params1st());
        driver.set("rg", TransformMaskeredInput.format(param("rg")));
        if(!driver.save()){
            
            
            
            
        }else{
            
            
        }
        */
    }

    @Override
    public void delete(@NotNull Context ctx, @NotNull String contentId){
        /*

        Driver m = Driver.findById(Integer.parseInt(getId()));
        String name = m.getString("name");
        m.delete();
        
        */
    }

    @Override
    public void update(@NotNull Context ctx, @NotNull String contentId){
        /*
        Driver driver = new Driver();
        driver.fromMap(params1st());
        driver.set("id", Integer.parseInt(param("id")));
        driver.set("rg", TransformMaskeredInput.format(param("rg")));
        if(!driver.save()){
            
            
        }
        else{
            
            
        }
        */
    }
}
