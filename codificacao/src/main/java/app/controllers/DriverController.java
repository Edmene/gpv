package app.controllers;

import app.json.DestinationJson;
import app.json.DriverJson;
import app.models.Destination;
import app.models.Driver;
import app.utils.Db;
import app.utils.TransformMaskeredInput;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.jetbrains.annotations.NotNull;
import app.utils.DocumentValidation;

public class DriverController extends GenericAppController {

    @Override
    public void getAll(@NotNull Context ctx){
        
    }

    @Override
    public void create(@NotNull Context ctx){
        try {
            DocumentValidation validation = new DocumentValidation();
            Base.open(Db.getInstance());
            Driver driver = new Driver();
            DriverJson driverJson  = ctx.bodyAsClass(DriverJson.class);
            driverJson.setAttributesOfDriver(driver);
            if(validation.validateCpf(driverJson.cpf) && validation.validateChn(driverJson.cnh)) {
                if (driver.saveIt()) {
                    ctx.res.setStatus(200);
                } else {
                    ctx.res.setStatus(400);
                }
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
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
