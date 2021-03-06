package app.controllers;

import app.json.ActivePeriodJson;
import app.models.ActivePeriod;
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

    public void getOne(@NotNull Context ctx, @NotNull String resourceId, @NotNull String planId) {
        try {
            Base.open(Db.getInstance());
            ActivePeriod activePeriod = ActivePeriod.findByCompositeKeys(Integer.parseInt(resourceId),
                    Integer.parseInt(planId));
            if(activePeriod == null){
                ctx.res.setStatus(404);
            }
            else {
                ctx.res.setStatus(200);
                ActivePeriodJson activePeriodJson = new ActivePeriodJson(activePeriod);
                ctx.result(mapper.writeValueAsString(activePeriodJson));
            }
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
        }
        catch (Exception e) {
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    public void update(@NotNull Context ctx, @NotNull String resourceId, @NotNull String planId) {
        try {
            Base.open(Db.getInstance());
            ActivePeriodJson activePeriodJson = ctx.bodyAsClass(ActivePeriodJson.class);
            ActivePeriod activePeriod = ActivePeriod.findByCompositeKeys(Integer.parseInt(resourceId),
                    Integer.parseInt(planId));
            if(activePeriod == null) {
                ctx.res.setStatus(404);
                return;
            }
            if(activePeriod.getId() != activePeriodJson.key){
                ctx.res.setStatus(400);
                return;
            }
            activePeriodJson.setAttributesOfActivePeriod(activePeriod);
            if (activePeriod.save()) {
                ctx.res.setStatus(200);
            } else {
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

    public void delete(@NotNull Context ctx, @NotNull String resourceId, @NotNull String planId) {
        try {
            Base.open(Db.getInstance());
            ActivePeriod activePeriod = ActivePeriod.findByCompositeKeys(Integer.parseInt(resourceId),
                    Integer.parseInt(planId));
            if(activePeriod == null){
                ctx.res.setStatus(404);
            }
            else {
                if (activePeriod.delete()) {
                    ctx.res.setStatus(200);
                } else {
                    ctx.res.setStatus(400);
                }
            }
            Base.close();
        } catch (Exception e) {
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }
}
            

