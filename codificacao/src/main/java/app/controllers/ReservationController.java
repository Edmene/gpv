package app.controllers;

import app.controllers.authorization.Protected;
import app.enums.Day;
import app.enums.Direction;
import app.enums.Shift;
import app.json.ReservationJson;
import app.models.*;
import app.utils.DateOfDayFinder;
import com.google.gson.*;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.annotations.POST;

import java.util.*;

@Protected
public class ReservationController extends GenericAppController {
    @Override
    public void index() {
        view("destinations", Destination.findAll());
    }

    public void planSelection(){
        LazyList<DestinationPlan> destinationPlanLazyList = DestinationPlan.find("destination_id = ?",
                Integer.parseInt(getId())).include(Plan.class);
        List<Map<String, Object>> destinationPlanMap = destinationPlanLazyList.toMaps();

        view("destinationPlanMapList", destinationPlanMap,
                "destination", getId());
    }


    @POST
    public void availabilitySelection(){
        LinkedList<List<Map<String, Object>>> lazyLists = new LinkedList<>();
        for(int day = 0;day < Day.values().length; day++){
            for (int shift = 0; shift < Shift.values().length; shift++){
                List<Map<String, Object>> mapList = Availability.find("plan_id = ?" +
                                "AND shift = ? AND day = ?",
                        Integer.parseInt(param("plan")),
                        shift, day).include(Stop.class).toMaps();
                List<Map<String, Object>> mapListFinal = new ArrayList<>();
                for(Map<String, Object> map : mapList){
                    if(map.containsKey("stop")) {
                        TreeMap stop = (TreeMap) map.get("stop");
                        map.put("address", Address.find("id = ?",(stop.get("address_id"))).get(0).toMap());
                        mapListFinal.add(map);
                    }
                    else {
                        mapListFinal.add(map);
                    }
                }
                lazyLists.add(mapListFinal);
            }
        }

        view("days", Day.values(),
                "shifts", Shift.values(),
                "destination", Integer.parseInt(param("destination")),
                "plan", Plan.findById(Integer.parseInt(param("plan"))),
                "directions", Direction.values(),
                "availabilitiesSubSets", lazyLists);
        //aqui eu cadastro ou armazeno no minimo o destination_plan.
    }

    @POST
    public void availabilityConfirmation(){
        ArrayList<ReservationJson> reservationJsonList = new ArrayList<>();
        Gson g = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(param("json")).getAsJsonArray();
        for(JsonElement element : jsonArray){
            ReservationJson reservationJson = g.fromJson(element.getAsJsonObject(), ReservationJson.class);
            reservationJsonList.add(reservationJson);
        }
        view("days", Day.values(),
                "json", param("json"),
                "reservation_type", param("reservation_type"),
                "destination", param("destination"),
                "plan", param("plan"),
                "listOfDates", new DateOfDayFinder().datesArrayList(reservationJsonList)
                );
    }

    @POST
    public void addReservations(){
        Reservation reservation = new Reservation();
        //reservationJson.setAttributesOfReservation(reservation);
        reservation.set("plan_id", Integer.parseInt(param("plan")),
                "passenger_id", session().get("id"),
                "status", true,
                "reservation_type", param("reservation_type"));
    }

}
