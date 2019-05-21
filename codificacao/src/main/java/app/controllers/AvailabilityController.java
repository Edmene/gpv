package app.controllers;

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
    private String shiftValues[]= {"11","17","03"};

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

    @Override
    public void getOne(@NotNull Context ctx, @NotNull String resourceId){
        try{
            Base.open(Db.getInstance());
            Availability availability = Availability.findById(Integer.parseInt(resourceId));
            AvailabilityJson availabilityJson = new AvailabilityJson(availability);
            ctx.result(mapper.writeValueAsString(availabilityJson));
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

    @Override
    public void update(@NotNull Context ctx, @NotNull String resourceId){
        try {
            Base.open(Db.getInstance());
            Availability availability = new Availability();
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

    @Override
    public void delete(@NotNull Context ctx, @NotNull String resourceId){
        try{
            Base.open(Db.getInstance());
            Availability availability = Availability.findById(Integer.parseInt(resourceId));
            if(availability.delete()){
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

    public void plan(){
        /*
        view("availabilities", AvailabilityDriverVehicle.find("plan_id = ?",
                Integer.parseInt(getId())).orderBy("day,shift,direction").toMaps(), "plan", getId(),
                "days", Day.values(), "shifts", Shift.values(),
                "directions", Direction.values());
        */
    }

    public void stopsOfDestination(){
        /*
        if(xhr()){

            Map<String, String> map = params1st();
            Gson g = new Gson();
            JsonParser jsonParser = new JsonParser();
            jsonParser.parse(String.valueOf(map.keySet().toArray()[0])).getAsJsonObject();
            ShiftsEnableJson shiftsEnableJson = g.fromJson(jsonParser.parse(
                    String.valueOf(map.keySet().toArray()[0])).getAsJsonObject(), ShiftsEnableJson.class);


            LazyList<DestinationPlanCity> destinationPlanCities = DestinationPlanCity.find("plan_id = ?",
                    shiftsEnableJson.plan);

            //Forces a selection of stops in order to initialize the list.
            LazyList<StopsInfo> filteredStopsList = StopsInfo.find("");

            filteredStopsList.removeAll(filteredStopsList);
            for(DestinationPlanCity destinationPlanCity : destinationPlanCities){
                Integer cityId = destinationPlanCity.getInteger("city_id");

                if(shiftsEnableJson.morning) {
                    filteredStopsList = StopsInfo.find("time < ? AND time >= ? AND city_id = ?",
                            LocalTime.parse(shiftValues[0] + ":00", DateTimeFormatter.ofPattern("HH:mm")),
                            LocalTime.parse(shiftValues[2] + ":00", DateTimeFormatter.ofPattern("HH:mm")),
                            cityId);
                }
                if(shiftsEnableJson.afternoon){
                    filteredStopsList.addAll(StopsInfo.find("time < ? AND time >= ? AND city_id = ?",
                            LocalTime.parse(shiftValues[1]+":00",DateTimeFormatter.ofPattern("HH:mm")),
                            LocalTime.parse(shiftValues[0]+":00",DateTimeFormatter.ofPattern("HH:mm")),
                            cityId));
                }
                if(shiftsEnableJson.night){
                    filteredStopsList.addAll(StopsInfo.find("time >= ? AND city_id = ?",
                            LocalTime.parse(shiftValues[1]+":00",DateTimeFormatter.ofPattern("HH:mm")),
                            cityId));
                    filteredStopsList.addAll(StopsInfo.find("time >= ? AND time < ? AND city_id = ?",
                            LocalTime.parse("00:00",DateTimeFormatter.ofPattern("HH:mm")),
                            LocalTime.parse(shiftValues[2]+":00",DateTimeFormatter.ofPattern("HH:mm")),
                            cityId));
                }
            }

            //LazyList<Stop> filteredStopsList = Stop.find("");
            respond(filteredStopsList.toJson(false)).contentType("application/json").status(200);
        }
        */
    }

    public void stopsOfBase(){
        /*
        LazyList<StopsInfo> filteredStopsList = StopsInfo.find("");

        Map<String, String> map = params1st();
        Gson g = new Gson();
        JsonParser jsonParser = new JsonParser();
        jsonParser.parse(String.valueOf(map.keySet().toArray()[0])).getAsJsonObject();
        ShiftsEnableJson shiftsEnableJson = g.fromJson(jsonParser.parse(
                String.valueOf(map.keySet().toArray()[0])).getAsJsonObject(), ShiftsEnableJson.class);

        filteredStopsList.removeAll(filteredStopsList);

        if(shiftsEnableJson.morning) {
            filteredStopsList = StopsInfo.find("time < ? AND time >= ? AND city_id = ?",
                    LocalTime.parse(shiftValues[0] + ":00", DateTimeFormatter.ofPattern("HH:mm")),
                    LocalTime.parse(shiftValues[2] + ":00", DateTimeFormatter.ofPattern("HH:mm")),
                    shiftsEnableJson.baseCity);
        }
        if(shiftsEnableJson.afternoon){
            filteredStopsList.addAll(StopsInfo.find("time < ? AND time >= ? AND city_id = ?",
                    LocalTime.parse(shiftValues[1]+":00",DateTimeFormatter.ofPattern("HH:mm")),
                    LocalTime.parse(shiftValues[0]+":00",DateTimeFormatter.ofPattern("HH:mm")),
                    shiftsEnableJson.baseCity));
        }
        if(shiftsEnableJson.night){
            filteredStopsList.addAll(StopsInfo.find("time >= ? AND city_id = ?",
                    LocalTime.parse(shiftValues[1]+":00",DateTimeFormatter.ofPattern("HH:mm")),
                    shiftsEnableJson.baseCity));
            filteredStopsList.addAll(StopsInfo.find("time >= ? AND time < ? AND city_id = ?",
                    LocalTime.parse("00:00",DateTimeFormatter.ofPattern("HH:mm")),
                    LocalTime.parse(shiftValues[2]+":00",DateTimeFormatter.ofPattern("HH:mm")),
                    shiftsEnableJson.baseCity));
        }

        respond(filteredStopsList.toJson(false)).contentType("application/json").status(200);
        */

    }

    public void addStop() throws IOException {
        /*
        String json = Util.read(getRequestInputStream());

        ArrayList<Availability> availabilityList = new ArrayList<>();
        Gson g = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
        for (JsonElement element : jsonArray) {
            Availability availability = new Availability();
            AvailabilityJson reservationJson = g.fromJson(element.getAsJsonObject(), AvailabilityJson.class);
            reservationJson.setAttributesOfAvailability(availability);

            availabilityList.add(availability);
        }
        InsertionException errorsInInsertion = sendAvailabilitiesQuery(availabilityList);
        if(errorsInInsertion != null){
            if(errorsInInsertion == InsertionException.CONFLICT){
                respond("Planos conflitantes").status(202);
            }
            else {
                respond("Entradas repetidas").status(202);
            }

        }
        else {
            respond("Sucesso").status(201);
        }

        */
    }

    public void newForm(){
        /*
        if(DestinationPlan.find("plan_id = ?", Integer.parseInt(getId())).size() > 0 &&
                Driver.findAll().size() > 0 && Vehicle.findAll().size() > 0) {
            ArrayList<TreeMap<String, Object>> shifts = new ArrayList<>();
            String shiftValues[] = {"12", "18", "04"};
            for (int i = 0; i < Shift.values().length; i++) {
                TreeMap<String, Object> shift = new TreeMap<>();
                shift.put("name", Shift.values()[i]);
                boolean hasStops = false;
                if (i == 0) {
                    if (Stop.find("time < ? AND time >= ?",
                            LocalTime.parse(shiftValues[i] + ":00", DateTimeFormatter.ofPattern("HH:mm")),
                            LocalTime.parse(shiftValues[2] + ":00", DateTimeFormatter.ofPattern("HH:mm"))).size() > 0) {
                        hasStops = true;
                    }
                } else {
                    if (i == 1) {
                        if (Stop.find("time < ? AND time >= ?",
                                LocalTime.parse(shiftValues[i] + ":00", DateTimeFormatter.ofPattern("HH:mm")),
                                LocalTime.parse(shiftValues[i - 1] + ":00", DateTimeFormatter.ofPattern("HH:mm"))).size() > 0) {
                            hasStops = true;
                        }
                    }
                    if (i == 2) {
                        if (Stop.find("time >= ?",
                                LocalTime.parse(shiftValues[1] + ":00", DateTimeFormatter.ofPattern("HH:mm"))).size() > 0) {
                            hasStops = true;
                        } else {
                            if (Stop.find("time >= ? AND time < ?",
                                    LocalTime.parse("00:00", DateTimeFormatter.ofPattern("HH:mm")),
                                    LocalTime.parse(shiftValues[i] + ":00", DateTimeFormatter.ofPattern("HH:mm"))).size() > 0) {
                                hasStops = true;
                            }
                        }

                    }
                }
                if (hasStops) {
                    shift.put("hasStops", true);
                } else {
                    shift.put("hasStops", false);
                }
                shifts.add(shift);
            }
            
                    "vehicles", Vehicle.findAll().toMaps(),
                    "plan", getId(),
                    "days", Day.values(),
                    "shifts", shifts,
                    "directions", Direction.values(),
                    "states", State.findAll().toMaps(),
                    "availability", true);
        }
        else {
            
            
        }
        */
    }

    private Integer toInt(String s){
        return Integer.parseInt(s);
    }


    public void alterStatus() {
        /*
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
