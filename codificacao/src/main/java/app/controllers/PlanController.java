package app.controllers;

import app.models.Destination;
import app.models.DestinationPlan;
import app.models.Plan;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

import java.util.Arrays;
import java.util.List;

public class PlanController extends GenericAppController {

    @Override
    public void index(){
        view("plans", Plan.findAll().toMaps());
    }

    @Override
    public void show(){
        Plan plan = Plan.findById(Integer.parseInt(getId()));
        if(plan != null){
            view("plan", plan);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override @POST
    public void create(){
        Plan plan = new Plan();
        plan.fromMap(params1st());
        String ticketPrice = param("ticket_price");
        String dailyValue = param("daily_value");
        if(ticketPrice.trim().length() == 0){
            ticketPrice = "0";
        }
        if(dailyValue.trim().length() == 0){
            dailyValue = "0";
        }
        plan.set("ticket_price", Float.parseFloat(ticketPrice));
        plan.set("daily_value", Float.parseFloat(dailyValue));
        if(param("available_reservations").length() <= 0){
            flash("message", "Preencha o campo numero de reservas");
            flash("errors", plan.errors());
            flash("params", params1st());
            redirect(PlanController.class, "new_form");
        }
        else {
            plan.set("available_reservations", Short.parseShort(param("available_reservations")));
            if (!plan.save()) {
                flash("message", "Something went wrong, please  fill out all fields");
                flash("errors", plan.errors());
                flash("params", params1st());
                redirect(PlanController.class, "new_form");
            } else {
                flash("message", "Novo plano adicionado");
                redirect(PlanController.class);
            }
        }
    }

    @Override @DELETE
    public void delete(){
        Plan plan = Plan.findById(Integer.parseInt(getId()));
        plan.delete();
        flash("message", "Plano deletado");
        redirect(PlanController.class);
    }

    @Override @PUT
    public void alterForm(){
        Plan plan = Plan.findById(Integer.parseInt(getId()));
        if(plan != null){
            view("plan", plan);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override @POST
    public void update(){
        Plan plan = new Plan();
        plan.fromMap(params1st());
        plan.set("id", Integer.parseInt(param("id")));
        String ticketPrice = param("ticket_price");
        String dailyValue = param("daily_value");
        if(ticketPrice.trim().length() == 0){
            ticketPrice = "0";
        }
        if(dailyValue.trim().length() == 0){
            dailyValue = "0";
        }
        plan.set("ticket_price", Float.parseFloat(ticketPrice));
        plan.set("daily_value", Float.parseFloat(dailyValue));
        if(param("available_reservations").length() <= 0){
            flash("message", "Preencha o campo numero de reservas");
            redirect(PlanController.class);
        }
        else {
            plan.set("available_reservations", Short.parseShort(param("available_reservations")));
            if (!plan.save()) {
                flash("message", "Something went wrong, please fill out all fields");
                redirect(PlanController.class);
            } else {
                flash("message", "Plano Alterado");
                redirect(PlanController.class);
            }
        }
    }

    //DestinationPlan Model related methods


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

    @PUT
    public void rmDestination(){
        LazyList destinationsPlan = DestinationPlan.find("plan_id = ?", Integer.parseInt(getId()));
        if(destinationsPlan != null){
            view("destinationsPlan", destinationsPlan.toMaps());
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

    @POST
    public void rmDestinations(){
        LazyList destinationsPlan = DestinationPlan.find("plan_id = ?",
                Integer.parseInt(param("plan")));
        List destinations = Arrays.asList(param("destinations").split(","));
        for(Object destination : destinationsPlan){
            DestinationPlan destinationPlan = (DestinationPlan) destination;
            if(!destinations.contains(destinationPlan.get("destination_id").toString())){
                destinationPlan.delete();
            }
        }
        redirect(PlanController.class);
    }

}
