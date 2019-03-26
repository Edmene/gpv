package app.controllers;

import app.controllers.authorization.ProtectedAdministrative;
import app.models.Driver;
import app.utils.TransformMaskeredInput;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

@ProtectedAdministrative
public class DriverController extends GenericAppController {

    @Override
    public void index(){
        
    }

    @Override @POST
    public void create(){
        Driver driver = new Driver();
        driver.fromMap(params1st());
        driver.set("rg", TransformMaskeredInput.format(param("rg")));
        if(!driver.save()){
            
            
            
            
        }else{
            
            
        }
    }

    @Override @DELETE
    public void delete(){

        Driver m = Driver.findById(Integer.parseInt(getId()));
        String name = m.getString("name");
        m.delete();
        
        
    }

    @Override @PUT
    public void alterForm(){
        Driver driver = Driver.findById(Integer.parseInt(getId()));
        if(driver != null){
            
        }else{
            
            
        }
    }

    @Override @POST
    public void update(){
        Driver driver = new Driver();
        driver.fromMap(params1st());
        driver.set("id", Integer.parseInt(param("id")));
        driver.set("rg", TransformMaskeredInput.format(param("rg")));
        if(!driver.save()){
            
            
        }
        else{
            
            
        }
    }
}
