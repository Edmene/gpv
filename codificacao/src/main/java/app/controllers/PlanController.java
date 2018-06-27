package app.controllers;

import app.controllers.authorization.ProtectedAdministrative;
import app.models.*;
import app.utils.TransformMaskeredInput;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@ProtectedAdministrative
public class PlanController extends GenericAppController {

    @Override
    public void index(){
        view("plans", Plan.findAll().toMaps());
    }

    private void detailsOfPlan(){
        Plan plan = Plan.findById(Integer.parseInt(getId()));
        Integer count = 0;
        if(!CountPassenger.find("plan_id = ?", Integer.parseInt(getId())).isEmpty()) {
            count = CountPassenger.find(
                    "plan_id = ?", Integer.parseInt(getId())).get(0)
                    .getInteger("num_passengers");
        }
        if(plan != null){
            view("plan", plan, "passengers", count);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override
    public void show(){
        detailsOfPlan();
    }

    @Override @POST
    public void create(){
        Plan plan = new Plan();
        plan.fromMap(params1st());
        String ticketPrice = TransformMaskeredInput.format(param("ticket_price"), ".");
        String dailyValue = TransformMaskeredInput.format(param("daily_value"), ".");
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
        detailsOfPlan();
    }

    @Override @POST
    public void update(){
        Plan plan = new Plan();
        plan.fromMap(params1st());
        plan.set("id", Integer.parseInt(param("id")));
        String ticketPrice = TransformMaskeredInput.format(param("ticket_price"),".");
        String dailyValue = TransformMaskeredInput.format(param("daily_value"), ".");
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
            view("plan", plan, "states", State.findAll().toMaps(),
                    "destination", true,  "add", true);
            //view("destinations", Destination.findAll().toMaps());
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }

    }

    @PUT
    public void rmDestination(){
        List<Map<String, Object>> destinationsPlan = DestinationPlan.find("plan_id = ?", Integer.parseInt(getId()))
                .include(Destination.class).toMaps();
        if(destinationsPlan != null){
            view("destinationsPlan", destinationsPlan,
                    "destination", true);
            //view("destinations", Destination.findAll().toMaps());
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }

    }

    @POST
    public void addDestinations() {
        if(param("items").contains(",")) {
            String[] destinations = param("items").split(",");
            for (String destination : destinations) {
                if (PassengerPlans.findByCompositeKeys(Integer.parseInt(destination),
                        Integer.parseInt(param("plan"))) == null) {

                    DestinationPlan destinationPlan = new DestinationPlan();
                    destinationPlan.set("destination_id", Integer.parseInt(destination),
                            "plan_id", Integer.parseInt(param("plan")));
                    destinationPlan.insert();
                } else {
                    flash("message", "Destinos ja vinculados ao plano previamente");
                }
            }
        }
        else {
            if (PassengerPlans.findByCompositeKeys(Integer.parseInt(param("items")),
                    Integer.parseInt(param("plan"))) == null) {

                DestinationPlan destinationPlan = new DestinationPlan();
                destinationPlan.set("destination_id", Integer.parseInt(param("items")),
                        "plan_id", Integer.parseInt(param("plan")));
                destinationPlan.insert();
                flash("message", "Destino adicionados com sucesso");
            } else {
                flash("message", "Destino ja vinculados ao plano previamente");
            }
        }
        redirect(PlanController.class);
    }

    @POST
    public void rmDestinations(){
        LazyList destinationsPlan = DestinationPlan.find("plan_id = ?",
                Integer.parseInt(param("plan")));
        List destinations = new LinkedList();
        if(param("item") != null) {
            destinations = Arrays.asList(param("items").split(","));
        }

        for(Object destination : destinationsPlan){
            DestinationPlan destinationPlan = (DestinationPlan) destination;
            if(!destinations.contains(destinationPlan.get("destination_id").toString())){
                if(PassengerPlans.find("plan_id = ?" +
                        " AND destination_id = ?", Integer.parseInt(param("plan")),
                        destinationPlan.getInteger("destination_id")).isEmpty()) {
                    destinationPlan.delete();
                }
                else {
                    flash("message", "Destinos referenciados nao e possivel remove-lo do plano");
                }
            }
        }
        redirect(PlanController.class);
    }

}
