package app.controllers;

import app.models.*;
import app.utils.TransformMaskeredInput;
import io.javalin.Context;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class PlanController extends GenericAppController {

    @Override
    public void getAll(@NotNull Context ctx){

    }

    private void detailsOfPlan(){
        /*
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
            
        }
        */
    }


    @Override
    public void create(@NotNull Context ctx){
        /*
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
            
            
            
            
        }
        else {
            plan.set("available_reservations", Short.parseShort(param("available_reservations")));
            if (!plan.save()) {
                
                
                
                
            } else {
                
                
            }
        }
        */
    }

    @Override
    public void delete(@NotNull Context ctx, @NotNull String contentId){
        /*
        Plan plan = Plan.findById(Integer.parseInt(getId()));
        plan.delete();
        */
        
        
    }

    @Override
    public void update(@NotNull Context ctx, @NotNull String contentId){
        /*
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
            
            
        }
        else {
            plan.set("available_reservations", Short.parseShort(param("available_reservations")));
            if (!plan.save()) {
                
                
            } else {
                
                
            }
        }
        */
    }

    //DestinationPlan Model related methods



    public void addDestination(){
        /*
        Plan plan = Plan.findById(Integer.parseInt(getId()));
        if(plan != null){
            view("plan", plan, "states", State.findAll().toMaps(),
                    "destination", true,  "add", true);
            //view("destinations", Destination.findAll().toMaps());
        }else{
            view("message", "are you trying to hack the URL?");
            
        }
        */

    }


    public void rmDestination(){
        /*
        List<Map<String, Object>> destinationsPlan = DestinationPlan.find("plan_id = ?", Integer.parseInt(getId()))
                .include(Destination.class).toMaps();
        if(destinationsPlan != null){
            view("destinationsPlan", destinationsPlan,
                    "destination", true);
            //view("destinations", Destination.findAll().toMaps());
        }else{
            view("message", "are you trying to hack the URL?");
            
        }
        */

    }

    public void addDestinations() {
        /*
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
                
            } else {
                
            }
        }
        */
        
    }

    public void rmDestinations(){
        /*
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
                    
                }
            }
        }
        */
        
    }

}
