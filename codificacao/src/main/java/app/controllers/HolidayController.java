package app.controllers;

import app.controllers.authorization.Protected;
import app.models.Holiday;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

@Protected
public class HolidayController extends GenericAppController {

    @Override
    public void index(){
        view("holidays", Holiday.findAll().toMaps());
    }

    @Override @POST
    public void create(){
        Holiday holiday = new Holiday();
        holiday.fromMap(params1st());
        holiday.setShort("day", Short.parseShort(param("day")));
        holiday.setShort("month", Short.parseShort(param("month")));
        if(!holiday.save()){
            flash("message", "Something went wrong, please  fill out all fields");
            flash("errors", holiday.errors());
            flash("params", params1st());
            redirect(HolidayController.class, "new_form");
        }else{
            flash("message", "New holiday was added: " + holiday.get("name"));
            redirect(HolidayController.class);
        }
    }

    @Override @DELETE
    public void delete(){

        Holiday holiday = Holiday.findById(Integer.parseInt(getId()));
        String name = holiday.getString("name");
        holiday.delete();
        flash("message", "Feriado: '" + name + "' foi deletado");
        redirect(HolidayController.class);
    }

    @Override @PUT
    public void alterForm(){
        Holiday holiday = Holiday.findById(Integer.parseInt(getId()));
        if(holiday != null){
            view("holiday", holiday);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override @POST
    public void update(){
        Holiday holiday = new Holiday();
        holiday.fromMap(params1st());
        holiday.set("id", Integer.parseInt(param("id")));
        if(!holiday.save()){
            flash("message", "Something went wrong, please restart the process");
            redirect(HolidayController.class);
        }
        else{
            flash("message", "Destino alterado " + holiday.get("name"));
            redirect(HolidayController.class);
        }
    }
}
