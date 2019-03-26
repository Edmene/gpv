package app.controllers;

import app.models.Driver;
import app.utils.TransformMaskeredInput;

public class DriverController extends GenericAppController {

    @Override
    public void index(){
        
    }

    @Override
    public void create(){
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
    public void delete(){
        /*

        Driver m = Driver.findById(Integer.parseInt(getId()));
        String name = m.getString("name");
        m.delete();
        
        */
    }

    @Override
    public void alterForm(){
        /*
        Driver driver = Driver.findById(Integer.parseInt(getId()));
        if(driver != null){
            
        }else{
            
            
        }
        */
    }

    @Override
    public void update(){
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
