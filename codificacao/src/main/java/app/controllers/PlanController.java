package app.controllers;

import app.json.PlanJson;
import app.models.*;
import app.utils.Db;
import app.utils.TransformMaskeredInput;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public class PlanController extends GenericAppController {

    private ArrayList<PlanJson> plansToPlanJsonList(LazyList<Plan> plans){
        ArrayList<PlanJson> json = new ArrayList<>();
        for (Plan plan : plans) {
            json.add(new PlanJson(plan));
        }
        return json;
    }


    @Override
    public void getAll(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            LazyList<Plan> results = Plan.findAll();
            ctx.result(mapper.writeValueAsString(plansToPlanJsonList(results)));
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    @Override
    public void getOne(@NotNull Context ctx, @NotNull String resourceId){
        try{
            Base.open(Db.getInstance());
            Plan plan = Plan.findById(Integer.parseInt(resourceId));
            PlanJson stateJson = new PlanJson(plan);
            ctx.result(mapper.writeValueAsString(stateJson));
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
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
        try {
            Base.open(Db.getInstance());
            Plan plan = new Plan();
            PlanJson planJson = ctx.bodyAsClass(PlanJson.class);
            planJson.setAttributesOfPlan(plan);
            if(plan.saveIt()){
                ctx.res.setStatus(200);
            }
            else{
                ctx.res.setStatus(400);
            }
            Base.close();
        }
        catch (Exception e) {
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    @Override
    public void delete(@NotNull Context ctx, @NotNull String resourceId){
        try{
            Base.open(Db.getInstance());
            Plan plan = Plan.findById(Integer.parseInt(resourceId));
            if(plan.delete()){
                ctx.res.setStatus(200);
            }
            else{
                ctx.res.setStatus(400);
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
    public void update(@NotNull Context ctx, @NotNull String resourceId){
        try {
            Base.open(Db.getInstance());
            Plan plan = new Plan();
            PlanJson planJson = ctx.bodyAsClass(PlanJson.class);
            planJson.setAttributesOfPlan(plan);
            if(plan.save()){
                ctx.res.setStatus(200);
            }
            else{
                ctx.res.setStatus(400);
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
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
