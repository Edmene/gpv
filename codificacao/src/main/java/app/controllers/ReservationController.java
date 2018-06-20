package app.controllers;

import app.controllers.authorization.Protected;
import app.enums.Day;
import app.enums.Direction;
import app.enums.Shift;
import app.json.ReservationJson;
import app.models.*;
import app.utils.DateOfDayFinder;
import app.utils.TotalValueOfPlanSelection;
import com.google.gson.*;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.annotations.POST;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Protected
public class ReservationController extends GenericAppController {
    @Override
    public void index() {
        view("destinations", Destination.findAll());
    }

    public void planSelection() {
        LazyList<DestinationPlan> destinationPlanLazyList = DestinationPlan.find("destination_id = ?",
                Integer.parseInt(getId())).include(Plan.class);
        List<Map<String, Object>> destinationPlanMap = destinationPlanLazyList.toMaps();
        if (CountPassenger.findAll().size() > 0) {
            for (Map<String, Object> destinationMap : destinationPlanMap) {
                if (CountPassenger.find("plan_id = ?", destinationMap.get("plan_id")).size() > 0) {
                    destinationMap.put("num_passengers", CountPassenger.find("plan_id = ?", destinationMap.get("plan_id"))
                            .get(0).getInteger("num_passengers"));
                }
            }
        }
        view("destinationPlanMapList", destinationPlanMap,
                "destination", getId());
    }


    @POST
    public void availabilitySelection() {
        LinkedList<List<Map<String, Object>>> lazyLists = new LinkedList<>();
        for (int day = 0; day < Day.values().length; day++) {
            for (int shift = 0; shift < Shift.values().length; shift++) {
                List<Map<String, Object>> mapListFinal = AvailabilityStopAddress.find("plan_id = ?" +
                                "AND shift = ? AND day = ?",
                        Integer.parseInt(param("plan")),
                        shift, day).toMaps();
                lazyLists.add(mapListFinal);
            }
        }

        view("days", Day.values(),
                "shifts", Shift.values(),
                "destination", Integer.parseInt(param("destination")),
                "plan", Plan.findById(Integer.parseInt(param("plan"))),
                "directions", Direction.values(),
                "availabilitiesSubSets", lazyLists);
    }

    @POST
    public void availabilityConfirmation() {
        ArrayList<ReservationJson> reservationJsonList = new ArrayList<>();
        Gson g = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(param("json")).getAsJsonArray();
        for (JsonElement element : jsonArray) {
            ReservationJson reservationJson = g.fromJson(element.getAsJsonObject(), ReservationJson.class);
            reservationJsonList.add(reservationJson);
        }

        Plan plan = Plan.findById(Integer.parseInt(param("plan")));
        ArrayList<ArrayList<Map<String, Object>>> listOfDates = new DateOfDayFinder().datesArrayList(reservationJsonList);
        TotalValueOfPlanSelection totalValueOfPlanSelection = new TotalValueOfPlanSelection(listOfDates);
        Boolean type = false;

        if (param("reservation_type").contains("P")) {
            type = true;
        }

        view("days", Day.values(),
                "json", jsonArray,
                "reservation_type", param("reservation_type"),
                "destination", param("destination"),
                "plan", param("plan"),
                "listOfDates", listOfDates,
                "totalValue", totalValueOfPlanSelection.calculateTotalValue(type, plan)
        );
    }

    @POST
    public void addReservations() {

        ArrayList<Reservation> reservationList = new ArrayList<>();
        Gson g = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(param("json")).getAsJsonArray();
        for (JsonElement element : jsonArray) {
            Reservation reservation = new Reservation();
            ReservationJson reservationJson = g.fromJson(element.getAsJsonObject(), ReservationJson.class);
            reservationJson.setAttributesOfReservation(reservation);
            if (param("reservation_type").contains("P")) {
                for (Day day : Day.values()) {
                    if (!param(day.name()).isEmpty() && reservationJson.day == day.ordinal()) {
                        LocalDate date = LocalDate.from(DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(param(day.name())));
                        reservation.setDate("date", date);
                    }
                }
            }
            reservation.set("plan_id", Integer.parseInt(param("plan")),
                    "passenger_id", session().get("id"),
                    "status", true,
                    "reservation_type", param("reservation_type"));
            reservationList.add(reservation);
        }

        boolean hasRepeatedReservations = true;

        //This command exists in order to avoid a issue if there are no records in the reservation table
        if (CountPassenger.findAll().size() > 0) {
            hasRepeatedReservations = sendReservationsQuery(reservationList);
        } else {
            if ((Integer) Plan.findById(Integer.parseInt(param("plan_id"))).get("available_reservations") >
                    CountPassenger.find("plan_id = ?", Integer.parseInt(param("plan_id")))
                            .get(0).getInteger("num_passengers")) {
                hasRepeatedReservations = sendReservationsQuery(reservationList);
            }
        }

        if (!hasRepeatedReservations) {
            flash("message", "Reservas registradas. Algumas já estavam presentes e foram ignoradas.");
        } else {
            flash("message", "Reservas registradas com sucesso");
        }
        redirect(HomeController.class);

    }

    public void listPlanOfPassenger(){
        view("plans", PassengerPlans.find("passenger_id = ?", session().get("id"))
        .include(Destination.class));
    }

    public void reservationList(){
        view("shifts", Shift.values(),
                "days", Day.values(),
                "direction", Direction.values(),
                "reservations", ReservationInfoPassenger.find("passenger_id = ?" +
                        " AND plan_id = ?", session().get("id"),
                        Integer.parseInt(getId())));
    }

    public void planReservationList(){
        view("shifts", Shift.values(),
                "days", Day.values(),
                "direction", Direction.values(),
                "reservations", ReservationInfo.find("plan_id = ?",
                        Integer.parseInt(getId())));
    }


    private boolean sendReservationsQuery(ArrayList<Reservation> reservationList) {
        boolean hasRepeatedReservations = true;
        if ((Integer) Plan.findById(Integer.parseInt(param("plan_id"))).get("available_reservations") <=
                CountPassenger.find("plan_id = ?", Integer.parseInt(param("plan_id")))
                        .get(0).getInteger("num_passengers")) {
            hasRepeatedReservations = false;
            return hasRepeatedReservations;
        }
        for (Reservation reservation : reservationList) {
            Integer numResults = Reservation.find("(date = ? OR date is null) AND " +
                            "passenger_id = ? AND day = ? AND shift = ? AND direction = ? AND " +
                            "plan_id = ? AND driver_id = ? AND vehicle_id = ? AND stop_id = ?",
                    reservation.getDate("date"),
                    reservation.getInteger("passenger_id"),
                    reservation.getInteger("day"),
                    reservation.getInteger("shift"),
                    reservation.getInteger("direction"),
                    reservation.getInteger("plan_id"),
                    reservation.getInteger("driver_id"),
                    reservation.getInteger("vehicle_id"),
                    reservation.getInteger("stop_id")).size();
            if (numResults != 0) {
                hasRepeatedReservations = false;
            } else {
                reservation.insert();
            }
        }
        return hasRepeatedReservations;
    }

}
