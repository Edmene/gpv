package app.controllers;

import app.json.StopJson;
import app.models.Stop;
import app.utils.Db;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StopController extends GenericAppController {

    private ArrayList<StopJson> citiesToStopJsonList(LazyList<Stop> cities){
        ArrayList<StopJson> json = new ArrayList<>();
        for (Stop stop : cities) {
            json.add(new StopJson(stop));
        }
        return json;
    }

    @Override
    public void getAll(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            LazyList<Stop> results = Stop.findAll();
            ctx.result(mapper.writeValueAsString(citiesToStopJsonList(results)));
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
            Stop stop = Stop.findById(Integer.parseInt(resourceId));
            if(stop == null){
                ctx.res.setStatus(404);
            }
            else {
                ctx.res.setStatus(200);
                StopJson stopJson = new StopJson(stop);
                ctx.result(mapper.writeValueAsString(stopJson));
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
            Stop stop = new Stop();
            StopJson stopJson  = ctx.bodyAsClass(StopJson.class);
            stopJson.setAttributesOfStop(stop);
            if(stop.saveIt()){
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
            Stop stop = new Stop();
            StopJson stopJson = ctx.bodyAsClass(StopJson.class);
            stopJson.setAttributesOfStop(stop);
            if(stop.save()){
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
    public void delete(@NotNull Context ctx, @NotNull String resourceId){
        try{
            Base.open(Db.getInstance());
            Stop stop = Stop.findById(Integer.parseInt(resourceId));
            if(stop == null){
                ctx.res.setStatus(404);
            }
            else {
                if (stop.delete()) {
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

    /*
    Adicionar triggers para os processos de criacao, alteracao e delecao
     */

    public void stopsOfDestination(@NotNull Context ctx, @NotNull String resourceId){
        /*
        Aparentemente era para retornar as paradas disponiveis para o plano baseado nas
         cidades em que o plano iria abranger nos seus destinos

         -> OBS: PASSA PARA O CONTROLLER DE PARADAS

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

    public void stopsOfBase(@NotNull Context ctx, @NotNull String resourceId){
        /*
        Aparentemente era para retornar as paradas disponiveis para o plano baseado nas
         cidades em que o plano iria abranger nas suas paradas de partida ou iniciais.

         -> OBS: PASSA PARA O CONTROLLER DE PARADAS

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
}
