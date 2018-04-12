package app.controllers;

import app.models.Plan;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;

public class PlanController extends AppController {
    public void index(){
        view("plans", Plan.findAll().toMaps());
    }

    public void show(){
        Plan plan = Plan.findById(Integer.parseInt(getId()));
        if(plan != null){
            view("plan", plan);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @POST
    public void create(){
        Plan plan = new Plan();
        plan.fromMap(params1st());
        plan.set("ticket_price", Float.parseFloat(param("ticket_price")));
        plan.set("daily_value", Float.parseFloat(param("daily_value")));
        plan.set("available_reservations", Short.parseShort(param("available_reservations")));
        if(!plan.save()){
            flash("message", "Something went wrong, please  fill out all fields");
            flash("errors", plan.errors());
            flash("params", params1st());
            redirect(PlanController.class, "new_form");
        }else{
            flash("message", "Novo plano adicionado");
            redirect(PlanController.class);
        }
    }

    @DELETE
    public void delete(){
        Plan plan = Plan.findById(Integer.parseInt(getId()));
        plan.delete();
        flash("message", "Plano deletado");
        redirect(PlanController.class);
    }

    public void newForm(){}
}
