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
        view("drivers", Driver.findAll().toMaps());
    }

    @Override @POST
    public void create(){
        Driver driver = new Driver();
        driver.fromMap(params1st());
        driver.set("rg", TransformMaskeredInput.format(param("rg")));
        if(!driver.save()){
            flash("message", "Something went wrong, please  fill out all fields");
            flash("errors", driver.errors());
            flash("params", params1st());
            redirect(DriverController.class, "new_form");
        }else{
            flash("message", "New driver was added: " + driver.get("name"));
            redirect(DriverController.class);
        }
    }

    @Override @DELETE
    public void delete(){

        Driver m = Driver.findById(Integer.parseInt(getId()));
        String name = m.getString("name");
        m.delete();
        flash("message", "User: '" + name + "' was deleted");
        redirect(DriverController.class);
    }

    @Override @PUT
    public void alterForm(){
        Driver driver = Driver.findById(Integer.parseInt(getId()));
        if(driver != null){
            view("driver", driver);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override @POST
    public void update(){
        Driver driver = new Driver();
        driver.fromMap(params1st());
        driver.set("id", Integer.parseInt(param("id")));
        driver.set("rg", TransformMaskeredInput.format(param("rg")));
        if(!driver.save()){
            flash("message", "Something went wrong, please restart the process");
            redirect(DriverController.class);
        }
        else{
            flash("message", "Motorista alterado " + driver.get("name") + " " + driver.get("surname"));
            redirect(DriverController.class);
        }
    }
}
