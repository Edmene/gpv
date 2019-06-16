package app.controllers;

import app.enums.Day;
import app.enums.Direction;
import app.enums.InsertionException;
import app.enums.Shift;
import app.json.AvailabilityJson;
import app.json.CityJson;
import app.json.StopJson;
import app.models.*;
import app.utils.Db;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AvailabilityController extends GenericAppController{
    private String[] shiftValues = {"12", "18", "03"};

    private ArrayList<AvailabilityJson> availabilitiesToAvailabilityJsonList(LazyList<Availability> availabilitys){
        ArrayList<AvailabilityJson> json = new ArrayList<>();
        for (Availability availability : availabilitys) {
            json.add(new AvailabilityJson(availability));
        }
        return json;
    }

    private boolean isEqualAvailability(Availability availability, AvailabilityJson availabilityJson){
        return (availability.getInteger("day") == availabilityJson.day.ordinal() &&
                availability.getInteger("shift") == availabilityJson.shift.ordinal() &&
                availability.getInteger("direction") == availabilityJson.direction.ordinal() &&
                availability.getInteger("driver_id") == availabilityJson.driver &&
                availability.getInteger("vehicle_id") == availabilityJson.vehicle &&
                availability.getInteger("stop_id") == availabilityJson.stop &&
                availability.getInteger("plan_id") == availabilityJson.plan);
    }

    @Override
    public void getAll(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            LazyList<Availability> results = Availability.findAll();
            ctx.result(mapper.writeValueAsString(availabilitiesToAvailabilityJsonList(results)));
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    public void getOne(@NotNull Context ctx, @NotNull String planId,
                       @NotNull String driverId, @NotNull String vehicleId,
                       @NotNull String shiftId, @NotNull String dayId,
                       @NotNull String directionId, @NotNull String stopId){
        try{
            Base.open(Db.getInstance());
            Availability availability = Availability.findByCompositeKeys(toInt(dayId), toInt(shiftId),
                    toInt(directionId), toInt(planId), toInt(driverId), toInt(vehicleId), toInt(stopId));
            if(availability == null){
                ctx.res.setStatus(404);
            }
            else {
                ctx.res.setStatus(200);
                AvailabilityJson availabilityJson = new AvailabilityJson(availability);
                ctx.result(mapper.writeValueAsString(availabilityJson));
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
    public void create(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            Availability availability = new Availability();
            AvailabilityJson availabilityJson  = ctx.bodyAsClass(AvailabilityJson.class);
            availabilityJson.setAttributesOfAvailability(availability);
            if(isTimeMatchingShift(availabilityJson)) {
                availabilityJson.setAttributesOfAvailability(availability);
                if (availability.saveIt()) {
                    ctx.res.setStatus(200);
                }
                else {
                    ctx.res.setStatus(400);
                }
            }
            else{
                ctx.result("Shift doesn't match stop time");
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

    public void update(@NotNull Context ctx, @NotNull String planId,
                       @NotNull String driverId, @NotNull String vehicleId,
                       @NotNull String shiftId, @NotNull String dayId,
                       @NotNull String directionId, @NotNull String stopId){
        try {
            Base.open(Db.getInstance());
            Availability availability = Availability.findByCompositeKeys(toInt(dayId), toInt(shiftId),
                    toInt(directionId), toInt(planId), toInt(driverId), toInt(vehicleId), toInt(stopId));
            AvailabilityJson availabilityJson = ctx.bodyAsClass(AvailabilityJson.class);
            if(availability == null) {
                ctx.res.setStatus(404);
                return;
            }
            if(isEqualAvailability(availability, availabilityJson)){
                ctx.res.setStatus(400);
                return;
            }
            if(isTimeMatchingShift(availabilityJson)) {
                availabilityJson.setAttributesOfAvailability(availability);
                if (availability.save()) {
                    ctx.res.setStatus(200);
                }
                else {
                    ctx.res.setStatus(400);
                }
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

    public void delete(@NotNull Context ctx, @NotNull String planId,
                       @NotNull String driverId, @NotNull String vehicleId,
                       @NotNull String shiftId, @NotNull String dayId,
                       @NotNull String directionId, @NotNull String stopId){
        try{
            Base.open(Db.getInstance());
            Availability availability = Availability.findByCompositeKeys(toInt(dayId), toInt(shiftId),
                    toInt(directionId), toInt(planId), toInt(driverId), toInt(vehicleId), toInt(stopId));
            if(availability == null){
                ctx.res.setStatus(404);
            }
            else {
                if (availability.delete()) {
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

    private boolean isTimeMatchingShift(AvailabilityJson availabilityJson){
        StopJson stop = new StopJson(Stop.findById(availabilityJson.stop));

        if(availabilityJson.shift == Shift.Manha) {
            return stop.time.isBefore(LocalTime.parse(shiftValues[0] + ":00",DateTimeFormatter.ofPattern("HH:mm"))) &&
                    stop.time.isAfter(LocalTime.parse(shiftValues[2] + ":00", DateTimeFormatter.ofPattern("HH:mm")));
        }
        if(availabilityJson.shift == Shift.Tarde){
            return stop.time.isBefore(LocalTime.parse(shiftValues[1] + ":00",DateTimeFormatter.ofPattern("HH:mm"))) &&
                    stop.time.isAfter(LocalTime.parse(shiftValues[0] + ":00",DateTimeFormatter.ofPattern("HH:mm")));
        }
        if(availabilityJson.shift == Shift.Noite){
            return stop.time.isAfter(LocalTime.parse(shiftValues[1] + ":00",DateTimeFormatter.ofPattern("HH:mm"))) ||
                    stop.time.isBefore(LocalTime.parse(shiftValues[2]+":00",DateTimeFormatter.ofPattern("HH:mm")));
        }
        return false;
    }

    public void availabilitiesOfPlanWithDriverAndVehicle(Context ctx, String planId){
        try {
            Base.open(Db.getInstance());
            LazyList<AvailabilityDriverVehicle> results = AvailabilityDriverVehicle.find("plan_id = ?",
                    Integer.parseInt(planId));
            ctx.result(results.toJson(false));
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }

    }

    private Integer toInt(String text){
        return Integer.parseInt(text);
    }

}
