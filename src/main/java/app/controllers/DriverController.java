package app.controllers;

import app.models.Driver;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;

public class DriverController extends AppController {
    public void index(){
        view("drivers", Driver.findAll().toMaps());
    }

    @POST
    public void create(){
        Driver driver = new Driver();
        driver.fromMap(params1st());
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

    public void show(){
        //this is to protect from URL hacking
        Driver m = (Driver) Driver.findById(getId());
        if(m != null){
            view("driver", m);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @DELETE
    public void delete(){

        Driver m = Driver.findById(Integer.parseInt(getId()));
        //Integer id = Integer.valueOf(getId());
        //Driver m = (Driver) Driver.findBySQL("SELECT * FROM DRIVERS WHERE id = ?",id).get(0);

        String name = m.getString("name");
        m.delete();
        flash("message", "User: '" + name + "' was deleted");
        redirect(DriverController.class);
    }

    public void newForm(){}
}
