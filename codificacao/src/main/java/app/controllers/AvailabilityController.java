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
    private String[] shiftValues = {"11", "17", "03"};

    private ArrayList<AvailabilityJson> availabilitiesToAvailabilityJsonList(LazyList<Availability> availabilitys){
        ArrayList<AvailabilityJson> json = new ArrayList<>();
        for (Availability availability : availabilitys) {
            json.add(new AvailabilityJson(availability));
        }
        return json;
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
            Availability availability = Availability.findByCompositeKeys(dayId, shiftId,
                    directionId, planId, driverId, vehicleId, stopId);
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
            Availability availability = Availability.findByCompositeKeys(dayId, shiftId,
            directionId, planId, driverId, vehicleId, stopId);
            AvailabilityJson availabilityJson = ctx.bodyAsClass(AvailabilityJson.class);
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
            Availability availability = Availability.findByCompositeKeys(dayId, shiftId,
                    directionId, planId, driverId, vehicleId, stopId);
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
            return stop.time.isBefore(LocalTime.parse(shiftValues[0] + ":59",DateTimeFormatter.ofPattern("HH:mm"))) &&
                    stop.time.isAfter(LocalTime.parse(shiftValues[2] + ":59", DateTimeFormatter.ofPattern("HH:mm")));
        }
        if(availabilityJson.shift == Shift.Tarde){
            return stop.time.isBefore(LocalTime.parse(shiftValues[1] + ":59",DateTimeFormatter.ofPattern("HH:mm"))) &&
                    stop.time.isAfter(LocalTime.parse(shiftValues[0] + ":59",DateTimeFormatter.ofPattern("HH:mm")));
        }
        if(availabilityJson.shift == Shift.Noite){
            return stop.time.isAfter(LocalTime.parse(shiftValues[1] + ":59",DateTimeFormatter.ofPattern("HH:mm"))) ||
                    stop.time.isBefore(LocalTime.parse(shiftValues[2]+":59",DateTimeFormatter.ofPattern("HH:mm")));
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

    public void alterStatus() {
        /*

        -> Vira um trigger ativado pela delecao na base de dados

        Availability availability = Availability.findByCompositeKeys(
                Integer.parseInt(param("day")),
                Integer.parseInt(param("shift")),
                Integer.parseInt(param("direction")),
                Integer.parseInt(param("plan_id")),
                Integer.parseInt(param("driver_id")),
                Integer.parseInt(param("vehicle_id")),
                Integer.parseInt(param("stop_id")));
        boolean status = false;
        if(!availability.getBoolean("status")) {
            status = true;
        }
        availability.set("status", status);
        LazyList<Reservation> reservations = Reservation.find("day = ? AND " +
                        "shift = ? AND direction = ? AND plan_id = ? AND " +
                        "driver_id = ? AND vehicle_id = ? AND stop_id = ?",
                toInt(param("day")),
                toInt(param("shift")),
                toInt(param("direction")),
                toInt(param("plan_id")),
                toInt(param("driver_id")),
                toInt(param("vehicle_id")),
                toInt(param("stop_id")));
        if(!reservations.isEmpty()) {
            for (Reservation reservation : reservations) {
                reservation.set("status", status);
                reservation.save();
            }
            availability.saveIt();
            if(!status) {
                
            }
            else {
                
            }
        }
        else {
            availability.delete();
            
        }


        */
    }

    private InsertionException sendAvailabilitiesQuery(ArrayList<Availability> availabilityList){
        /*
        -> Forte candidato a se tornar uma trigger.

        VERIFICA CONFLITOS COM OUTROS PLANOS

         */
        InsertionException hasRepeatedReservations = null;

        for (Availability availability : availabilityList) {

            //Checks for same parameters with others plans
            LazyList availabilities = Availability.find(
                    "day = ? AND shift = ? AND direction = ? AND plan_id != ?",
                    availability.get("day"),
                    availability.get("shift"),
                    availability.get("direction"),
                    availability.get("plan_id"));
            if(availabilities.size() != 0){
                hasRepeatedReservations = InsertionException.CONFLICT;
                
            }

            if(hasRepeatedReservations != InsertionException.CONFLICT) {
                //Check if the availability is already registered
                Integer numResults = Availability.find("day = ? AND shift = ? AND " +
                                "driver_id = ? AND vehicle_id = ? AND stop_id = ? AND " +
                                "plan_id = ?",
                        availability.get("day"),
                        availability.get("shift"),
                        availability.get("driver_id"),
                        availability.get("vehicle_id"),
                        availability.get("stop_id"),
                        availability.get("plan_id")).size();
                if (numResults != 0) {
                    hasRepeatedReservations = InsertionException.REPEATED_ENTRIES;
                    
                } else {
                    availability.insert();
                }
            }
        }
        return hasRepeatedReservations;
    }
}
