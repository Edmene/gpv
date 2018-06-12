package app.controllers;

import app.enums.Day;
import app.enums.Direction;
import app.enums.Shift;
import app.json.ShiftsEnableJson;
import app.models.*;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AvailabilityController extends GenericAppController {
    private String shiftValues[]= {"12","18","04"};

    public void plan(){
        view("availabilities", Availability.find("plan_id = ?",
                Integer.parseInt(getId())).toMaps(), "plan", getId(),
                "days", Day.values(), "shifts", Shift.values(),
                "directions", Direction.values());
    }

    public void stopsOfDestination(){
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
    }

    public void stopsOfBase(){
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

    }

    @POST
    public void addStop(){
        List<Map<String, Object>> stopList = Stop.findAll().toMaps();
        for (Map<String, Object> stop : stopList){
            stop.put("address", Address.findById(stop.get("address_id")).toMap());
        }
        view("stops", stopList,
                "plan", param("plan"),
                "vehicle", param("vehicle"),
                "driver", param("driver"),
                "shift", param("shift"),
                "day", param("day"),
                "direction", param("direction"));
    }

    @POST
    public void addStops(){
        LazyList availabilities = Availability.find(
                "day = ? AND shift = ? AND direction = ?",
                Day.valueOf(param("day")).ordinal(),
                Shift.valueOf(param("shift")).ordinal(),
                Direction.valueOf(param("direction")).ordinal());
        Boolean allowAddition = true;
        if(availabilities.size() != 0){
            for(Object object : availabilities){
                Availability availability = (Availability) object;
                if(availability.getInteger("driver_id") == Integer.parseInt(param("driver")) ||
                    availability.getInteger("vehicle_id") == Integer.parseInt(param("vehicle"))){
                    flash("message", "Não é permitido alocar um motorista ou " +
                            "veiculo que conflite com uma disponibilidade existente de outro plano");
                    allowAddition = false;
                    redirect(PlanController.class);
                }
            }
        }
        if(allowAddition) {
            String[] stops = param("items").split(",");
            for (String stop : stops) {
                Availability availability = new Availability();
                availability.set(
                        "day", Day.valueOf(param("day")).ordinal(),
                        "shift", Shift.valueOf(param("shift")).ordinal(),
                        "direction", Direction.valueOf(param("direction")).ordinal(),
                        "plan_id", Integer.parseInt(param("plan")),
                        "driver_id", Integer.parseInt(param("driver")),
                        "vehicle_id", Integer.parseInt(param("vehicle")),
                        "stop_id", Integer.parseInt(stop));
                availability.insert();
            }
            flash("message", "Disponibiliade cadastrada");
            redirect(PlanController.class);
        }
    }

    @Override
    public void newForm(){
        if(DestinationPlan.find("plan_id = ?", Integer.parseInt(getId())).size() > 0) {
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
            view("drivers", Driver.findAll().toMaps(),
                    "vehicles", Vehicle.findAll().toMaps(),
                    "plan", getId(),
                    "days", Day.values(),
                    "shifts", shifts,
                    "directions", Direction.values(),
                    "states", State.findAll().toMaps());
        }
        else {
            flash("message", "Cadastre destinos antes de cadastrar disponibilidades");
            redirect(AvailabilityController.class, "plan", getId());
        }
    }

    @Override @DELETE
    public void delete() {
        Availability availability = new Availability();
        availability.set(
                "day", Integer.parseInt(param("day")),
                "shift", Integer.parseInt(param("shift")),
                "direction", Integer.parseInt(param("direction")),
                "plan_id", Integer.parseInt(param("plan_id")),
                "driver_id", Integer.parseInt(param("driver_id")),
                "vehicle_id", Integer.parseInt(param("vehicle_id")),
                "stop_id", Integer.parseInt(param("stop_id"))
        );
        availability.delete();
        flash("message", "A disponibilidae do plano foi deletada");
        redirect(PlanController.class);
    }
}
