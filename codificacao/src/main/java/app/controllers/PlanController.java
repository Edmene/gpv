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
            ctx.res.setStatus(200);
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
            if(plan == null){
                ctx.res.setStatus(404);
            }
            else {
                if (plan.delete()) {
                    ctx.res.setStatus(200);
                    PlanJson planJson = new PlanJson(plan);
                    ctx.result(mapper.writeValueAsString(planJson));
                } else {
                    ctx.res.setStatus(400);
                }
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    public void numPassengerOfPlan(Context ctx, String resourceId){
        try {
            Base.open(Db.getInstance());
            ctx.res.setStatus(200);
            ctx.result(CountPassenger.find(
                    "plan_id = ?", Integer.parseInt(resourceId)).toJson(false));
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }

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
            if(plan == null){
                ctx.res.setStatus(404);
            }
            else {
                if (plan.delete()) {
                    ctx.res.setStatus(200);
                } else {
                    ctx.res.setStatus(400);
                }
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

    /*
    Adicionar triggers para os processos de criacao, alteracao e delecao
     */

    public void listDestinations(@NotNull Context ctx, @NotNull String resourceId){
        try {
            Base.open(Db.getInstance());
            LazyList<PlanDestinations> pdList = PlanDestinations.find("plan_id = ?",
                    Integer.parseInt(resourceId));
            if(pdList.isEmpty()){
                ctx.res.setStatus(404);
            }
            else{
                ctx.result(pdList.toJson(false));
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    public void addDestination(@NotNull Context ctx, @NotNull String resourceId, @NotNull String destinationId){
        try {
            Base.open(Db.getInstance());
            DestinationPlan dp = new DestinationPlan();
            dp.set("destination_id", Integer.parseInt(destinationId),
                    "plan_id", Integer.parseInt(resourceId));
            if (dp.saveIt()) {
                ctx.res.setStatus(200);
            }
            else {
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

    public void rmDestination(@NotNull Context ctx, @NotNull String resourceId, @NotNull String destinationId){
        try {
            Base.open(Db.getInstance());
            DestinationPlan dp = DestinationPlan.findByCompositeKeys(Integer.parseInt(destinationId),
                    Integer.parseInt(resourceId));
            if(dp == null){
                ctx.res.setStatus(404);
                ctx.result("Not Found");
            }
            else {
                if (dp.delete()) {
                    ctx.res.setStatus(200);
                } else {
                    ctx.res.setStatus(400);
                }
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

}
