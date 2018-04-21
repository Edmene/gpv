package app.controllers;

import app.models.Holiday;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;

import java.text.ParseException;

public class HolidayController extends AppController {
    public void index(){
        view("holidays", Holiday.findAll().toMaps());
    }

    @POST
    public void create() throws ParseException {
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

    public void show(){
        //this is to protect from URL hacking
        Holiday holiday = Holiday.findById(Integer.parseInt(getId()));
        if(holiday != null){
            view("holiday", holiday);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @DELETE
    public void delete(){

        Holiday holiday = Holiday.findById(Integer.parseInt(getId()));
        String name = holiday.getString("name");
        holiday.delete();
        flash("message", "Feriado: '" + name + "' foi deletado");
        redirect(HolidayController.class);
    }

    public void newForm(){}
}
