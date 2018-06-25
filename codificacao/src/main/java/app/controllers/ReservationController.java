package app.controllers;

import app.controllers.authorization.Protected;
import app.enums.CalculationMethod;
import app.enums.Day;
import app.enums.Direction;
import app.enums.Shift;
import app.json.ReservationJson;
import app.json.ReservationsSearchFiltersJson;
import app.models.*;
import app.utils.DateOfDayFinder;
import app.utils.TotalValueOfPlanSelection;
import com.google.gson.*;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

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
                                "AND shift = ? AND day = ? AND status IS TRUE",
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
        CalculationMethod type = CalculationMethod.M;

        if (param("reservation_type").contains("P")) {
            type = CalculationMethod.T;
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
            reservation.set("plan_id", Integer.parseInt(param("plan_id")),
                    "passenger_id", session().get("id"),
                    "status", true,
                    "reservation_type", param("reservation_type"));
            reservationList.add(reservation);

        }

        if(PassengerPlans.find("plan_id = ? AND passenger_id = ? AND destination_id = ?",
                Integer.parseInt(param("plan_id")), session().get("id"),
                Integer.parseInt(param("destination"))).size() == 0) {
            PassengerPlans passengerPlans = new PassengerPlans();
            passengerPlans.set("plan_id", Integer.parseInt(param("plan_id")),
                    "passenger_id", session().get("id"),
                    "destination_id", Integer.parseInt(param("destination")));
            passengerPlans.save();
        }

        boolean hasRepeatedReservations = sendReservationsQuery(reservationList);
        if (!hasRepeatedReservations) {
            flash("message", "Reservas registradas. Algumas já estavam presentes e foram ignoradas.");
        } else {
            flash("message", "Reservas registradas com sucesso");
        }
        redirect(HomeController.class);

    }

    public void list(){
        view("reservations", ReservationInfoAgg.find("plan_id = ?",
                Integer.parseInt(getId())).toMaps(),
                "days", Day.values(),
                "shifts", Shift.values());
    }

    public void filteredList(){

        Map<String, String> map = params1st();
        Gson g = new Gson();
        JsonParser jsonParser = new JsonParser();
        jsonParser.parse(String.valueOf(map.keySet().toArray()[0])).getAsJsonObject();
        ReservationsSearchFiltersJson searchFiltersJson = g.fromJson(jsonParser.parse(
                String.valueOf(map.keySet().toArray()[0])).getAsJsonObject(), ReservationsSearchFiltersJson.class);

        String response;

        if(searchFiltersJson.day == 0){
            if(searchFiltersJson.shift == 0){
                response = ReservationInfoAgg.find("plan_id = ?", searchFiltersJson.plan_id).toJson(false);
            }
            else {
                response = ReservationInfoAggShift.find("plan_id = ? AND " +
                        "shift = ?", searchFiltersJson.plan_id, searchFiltersJson.shift).toJson(false);
            }
        }
        else {
            if(searchFiltersJson.shift == 0){
                response = ReservationInfoAggDay.find("plan_id = ? AND " +
                                "day = ?", searchFiltersJson.plan_id, searchFiltersJson.day).toJson(false);
            }
            else {
                response = ReservationInfoAggDayShift.find("plan_id = ? AND " +
                        "shift = ? AND day = ?", searchFiltersJson.plan_id,
                        searchFiltersJson.shift, searchFiltersJson.day).toJson(false);
            }
        }

        respond(response).contentType("application/json").status(200);

    }

    public void reservationList(){
        Integer passenger_id = Integer.parseInt(param("passenger_id"));
        Integer plan_id = Integer.parseInt(param("plan_id"));
        LazyList<Reservation> reservationPAList = Reservation.find(
                "passenger_id = ? AND plan_id = ? AND " +
                        "reservation_typxzae = ? AND status IS TRUE",
                passenger_id, plan_id, "P");

        LazyList<Reservation> reservationPIList = Reservation.find(
                "passenger_id = ? AND plan_id = ? AND " +
                        "reservation_type = ? AND status IS FALSE",
                passenger_id, plan_id, "P");

        LazyList<Reservation> reservationMList = Reservation.find(
                "passenger_id = ? AND plan_id = ? AND " +
                        "reservation_type = ? AND status IS TRUE",
                passenger_id, plan_id, "M");

        Plan plan = Plan.findById(plan_id);

        ArrayList<ReservationJson> reservationJsonListM = new ArrayList<>();
        for(Reservation reservationM : reservationMList){
            ReservationJson reservationJson = new ReservationJson(reservationM);
            reservationJsonListM.add(reservationJson);
        }
        ArrayList<ArrayList<Map<String, Object>>> listOfDatesM = new DateOfDayFinder().datesArrayList(reservationJsonListM);
        TotalValueOfPlanSelection totalValueOfReservationM = new TotalValueOfPlanSelection(listOfDatesM);


        view("shifts", Shift.values(),
                "days", Day.values(),
                "directions", Direction.values(),
                "reservations", ReservationInfoPassenger.find("passenger_id = ?" +
                        " AND plan_id = ?", passenger_id,
                        plan_id),
                "totalTicketActive", reservationPAList.size()*plan.getFloat("ticket_price"),
                "totalTicketInactive", reservationPIList.size()*plan.getFloat("ticket_price"),
                "totalMonthly", totalValueOfReservationM.calculateTotalValue(CalculationMethod.M, plan));
    }

    private Integer toInt(String s){
        return Integer.parseInt(s);
    }

    @PUT
    public void changeReservation(){
        Reservation reservation = (Reservation) Reservation.find("passenger_id = ? AND " +
                "day = ? AND shift = ? AND direction = ? AND plan_id = ? AND " +
                "driver_id = ? AND vehicle_id = ? AND stop_id = ?",
                    toInt(param("passenger_id")),
                    toInt(param("day")),
                    toInt(param("shift")),
                    toInt(param("direction")),
                    toInt(param("plan_id")),
                    toInt(param("driver_id")),
                    toInt(param("vehicle_id")),
                    toInt(param("stop_id"))).get(0);
        if(reservation.getBoolean("status")) {

            if (reservation.getString("reservation_type").contains("M")) {
                if (reservation.getDate("alteration_date") == null) {
                    reservation.set("alteration_date", LocalDate.now().plusDays(15));
                    flash("message", "Reserva agendada para desativacao");
                }
                else {
                    reservation.set("alteration_date", null);
                    flash("message", "Desativacao de reserva cancelada");
                }
            }
            else {
                reservation.set("status", false);
                flash("message", "Reserva desativada");
            }

        }
        else {
            if(isAvailabilityStillActive(reservation)) {
                if (reservation.getDate("date") == null) {
                    if (isPassengerNumberNotExcessive(reservation)) {
                        reservation.set("status", true);
                        flash("message", "Reserva ativada");
                    } else {
                        flash("message", "Reserva nao pode ser ativada devido ao numero de reservas estar preenchido");
                    }
                } else {
                    if (LocalDate.now().isAfter(reservation.getDate("date").toLocalDate())) {
                        flash("message", "Reserva expirada");
                    } else {
                        if (isPassengerNumberNotExcessive(reservation)) {
                            reservation.set("status", true);
                            flash("message", "Reserva ativada");
                        } else {
                            flash("message", "Reserva nao pode ser ativada devido ao numero de reservas estar preenchido");
                        }
                    }
                }
            }
            else {
                flash("message", "Plano nao mais disponivel neste formato");
            }
        }
        reservation.save();
        redirect(ReservationController.class, "reservation_list", param("plan_id"));
    }

    private boolean isPassengerNumberNotExcessive(Reservation reservation){
        if(CountPassenger.find("plan_id = ?",
                reservation.getInteger("plan_id")).get(0).
                getInteger("num_passengers") < Plan.findById(
                reservation.getInteger("plan_id")).getShort("available_reservations")) {
            return true;
        }
        else {
            return false;
        }
    }
    
    private boolean isAvailabilityStillActive(Reservation reservation){
        Boolean isActive = Reservation.find("day = ? AND shift = ? AND " +
                        "direction = ? AND plan_id = ? AND " +
                        "driver_id = ? AND vehicle_id = ? AND stop_id = ?",
                reservation.getInteger("day"),
                reservation.getInteger("shift"),
                reservation.getInteger("direction"),
                reservation.getInteger("plan_id"),
                reservation.getInteger("driver_id"),
                reservation.getInteger("vehicle_id"),
                reservation.getInteger("stop_id")).get(0).getBoolean("status");
        return isActive;
    }

    private boolean sendReservationsQuery(ArrayList<Reservation> reservationList) {
        boolean hasRepeatedReservations = true;
        //This command exists in order to avoid a issue if there are no records in the reservation table
        if (CountPassenger.findAll().size() != 0) {
            if ((Integer) Plan.findById(Integer.parseInt(param("plan_id"))).get("available_reservations") <=
                    CountPassenger.find("plan_id = ?", Integer.parseInt(param("plan_id")))
                            .get(0).getInteger("num_passengers")) {
                hasRepeatedReservations = false;
                return hasRepeatedReservations;
            }
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
