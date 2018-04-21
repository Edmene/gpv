package app.controllers;

import app.models.Destination;
import app.models.DestinationPlan;
import app.models.Plan;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

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
        String ticketPrice = param("ticket_price");
        String dailyValue = param("daily_value");
        if(ticketPrice.length() == 0){
            ticketPrice = "0";
        }
        if(dailyValue.length() == 0){
            dailyValue = "0";
        }
        plan.set("ticket_price", Float.parseFloat(ticketPrice));
        plan.set("daily_value", Float.parseFloat(dailyValue));
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

    @PUT
    public void addDestination(){
        Plan plan = Plan.findById(Integer.parseInt(getId()));
        if(plan != null){
            view("plan", plan, "destinations", Destination.findAll().toMaps());
            //view("destinations", Destination.findAll().toMaps());
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }

    }

    @POST
    public void addDestinations(){
        String[] destinations = param("destinations").split(",");
        for (String destination : destinations) {
            DestinationPlan destinationPlan = new DestinationPlan();
            destinationPlan.set("destination_id", Integer.parseInt(destination),
                    "plan_id", Integer.parseInt(param("plan")));
            destinationPlan.insert();
        }
        redirect(PlanController.class);
    }
}
