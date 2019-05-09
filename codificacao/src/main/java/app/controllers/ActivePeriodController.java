package app.controllers;

import app.json.ActivePeriodJson;
import app.models.ActivePeriod;
import app.models.ActivePeriodPlan;
import app.utils.Db;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ActivePeriodController extends GenericAppController {

    private ArrayList<ActivePeriodJson> citiesToActivePeriodJsonList(LazyList<ActivePeriod> cities) {
        ArrayList<ActivePeriodJson> json = new ArrayList<>();
        for (ActivePeriod activePeriod : cities) {
            json.add(new ActivePeriodJson(activePeriod));
        }
        return json;
    }

    @Override
    public void getAll(@NotNull Context ctx) {
        try {
            Base.open(Db.getInstance());
            LazyList<ActivePeriod> results = ActivePeriod.findAll();
            ctx.result(mapper.writeValueAsString(citiesToActivePeriodJsonList(results)));
            Base.close();
        } catch (Exception e) {
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    @Override
    public void getOne(@NotNull Context ctx, @NotNull String resourceId) {
        try {
            Base.open(Db.getInstance());
            ActivePeriod activePeriod = ActivePeriod.findById(Integer.parseInt(resourceId));
            ActivePeriodJson stateJson = new ActivePeriodJson(activePeriod);
            ctx.result(mapper.writeValueAsString(stateJson));
            Base.close();
        } catch (Exception e) {
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    public void getByState(@NotNull Context ctx, @NotNull String resourceId) {
        try {
            Base.open(Db.getInstance());
            LazyList<ActivePeriod> cities = ActivePeriod.find("state_id = ?", Integer.parseInt(resourceId));
            ctx.result(mapper.writeValueAsString(citiesToActivePeriodJsonList(cities)));
            Base.close();
        } catch (Exception e) {
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }


    @Override
    public void create(@NotNull Context ctx) {
        try {
            Base.open(Db.getInstance());
            ActivePeriod activePeriod = new ActivePeriod();
            ActivePeriodJson activePeriodJson = ctx.bodyAsClass(ActivePeriodJson.class);
            activePeriodJson.setAttributesOfActivePeriod(activePeriod);
            if (activePeriod.saveIt()) {
                ctx.res.setStatus(200);
            } else {
                ctx.res.setStatus(400);
            }
            Base.close();
        } catch (Exception e) {
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    @Override
    public void update(@NotNull Context ctx, @NotNull String resourceId) {
        try {
            Base.open(Db.getInstance());
            ActivePeriod activePeriod = new ActivePeriod();
            ActivePeriodJson activePeriodJson = ctx.bodyAsClass(ActivePeriodJson.class);
            activePeriodJson.setAttributesOfActivePeriod(activePeriod);
            if (activePeriod.save()) {
                ctx.res.setStatus(200);
            } else {
                ctx.res.setStatus(400);
            }
            Base.close();
        } catch (Exception e) {
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    @Override
    public void delete(@NotNull Context ctx, @NotNull String resourceId) {
        try {
            Base.open(Db.getInstance());
            ActivePeriod activePeriod = ActivePeriod.findById(Integer.parseInt(resourceId));
            if (activePeriod.delete()) {
                ctx.res.setStatus(200);
            } else {
                ctx.res.setStatus(400);
            }
            Base.close();
        } catch (Exception e) {
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    public void addActivePeriodToPlan(@NotNull Context ctx, @NotNull String resourceId, @NotNull String activePeriodId){
        try {
            Base.open(Db.getInstance());
            ActivePeriodPlan activePeriodPlan = new ActivePeriodPlan();
            activePeriodPlan.set("plan_id", resourceId,
                    "active_period_id", activePeriodId);
            if(activePeriodPlan.save()){
                ctx.res.setStatus(200);
            }
            else{
                ctx.res.setStatus(400);
            }
            Base.close();
        } catch (Exception e) {
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    public void removeActivePeriodToPlan(@NotNull Context ctx, @NotNull String resourceId, @NotNull String activePeriodId){
        try {
            Base.open(Db.getInstance());
            ActivePeriodPlan activePeriodPlan = new ActivePeriodPlan();
            activePeriodPlan.set("plan_id", resourceId,
                    "active_period_id", activePeriodId);
            if(activePeriodPlan.delete()){
                ctx.res.setStatus(200);
            }
            else{
                ctx.res.setStatus(400);
            }
            Base.close();
        } catch (Exception e) {
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }
}
            

