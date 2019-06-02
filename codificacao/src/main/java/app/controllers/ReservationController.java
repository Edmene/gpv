package app.controllers;

import app.controllers.authorization.Protected;
import app.enums.*;
import app.json.ReservationJson;
import app.json.ReservationsSearchFiltersJson;
import app.models.*;
import app.utils.DateOfDayFinder;
import app.utils.Db;
import app.utils.TotalValueOfPlanSelection;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Protected
public class ReservationController extends GenericAppController {

    private ArrayList<ReservationJson> reservationsToReservationJsonList(LazyList<Reservation> reservations){
        ArrayList<ReservationJson> json = new ArrayList<>();
        for (Reservation reservation : reservations) {
            json.add(new ReservationJson(reservation));
        }
        return json;
    }

    @Override
    public void getAll(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            LazyList<Reservation> results = Reservation.findAll();
            ctx.result(mapper.writeValueAsString(reservationsToReservationJsonList(results)));
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
                       @NotNull String directionId, @NotNull String stopId,
                       @NotNull String passengerId){
        try{
            Base.open(Db.getInstance());
            Reservation reservation = Reservation.findByCompositeKeys(dayId, shiftId,
                    directionId, planId, driverId, vehicleId, stopId, passengerId);
            if(reservation == null){
                ctx.res.setStatus(404);
            }
            else {
                if (reservation.delete()) {
                    ctx.res.setStatus(200);
                    ReservationJson reservationJson = new ReservationJson(reservation);
                    ctx.result(mapper.writeValueAsString(reservationJson));
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


    @Override
    public void create(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            Reservation reservation = new Reservation();
            ReservationJson reservationJson  = ctx.bodyAsClass(ReservationJson.class);
            reservationJson.setAttributesOfReservation(reservation);
            if (reservation.save()) {
                ctx.res.setStatus(200);
            }
            else {
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
                       @NotNull String directionId, @NotNull String stopId,
                       @NotNull String passengerId){
        try {
            Base.open(Db.getInstance());
            Reservation reservation = Reservation.findByCompositeKeys(dayId, shiftId,
                    directionId, planId, driverId, vehicleId, stopId, passengerId);
            ReservationJson reservationJson = ctx.bodyAsClass(ReservationJson.class);
            reservationJson.setAttributesOfReservation(reservation);
            if(reservation == null){
                ctx.res.setStatus(404);
            }
            else {
                if (reservation.delete()) {
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

    public void delete(@NotNull Context ctx, @NotNull String planId,
                       @NotNull String driverId, @NotNull String vehicleId,
                       @NotNull String shiftId, @NotNull String dayId,
                       @NotNull String directionId, @NotNull String stopId,
                       @NotNull String passengerId){
        try{
            Base.open(Db.getInstance());
            Reservation reservation = Reservation.findByCompositeKeys(dayId, shiftId,
                    directionId, planId, driverId, vehicleId, stopId, passengerId);
            if(reservation.delete()){
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

    /*
    Adicionar triggers para os processos de criacao, alteracao e delecao
     */

    public void planSelection() {
        /*
        if(!negateAccess(UserType.A)) {
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
            
                    "destination", getId());
        }
        */
    }


    public void availabilitySelection() {
        /*
        if(!negateAccess(UserType.A)) {
            LinkedList<List<List<Map<String, Object>>>> lazyLists = new LinkedList<>();
            for (int day = 0; day < Day.values().length; day++) {
                List<List<Map<String, Object>>> mapListFinal = new LinkedList<>();
                for (int shift = 0; shift < Shift.values().length; shift++) {
                    List<Map<String, Object>> mapList = AvailabilityStopAddress.find("plan_id = ?" +
                                    "AND shift = ? AND day = ? AND status IS TRUE",
                            Integer.parseInt(param("plan")),
                            shift, day).toMaps();
                    mapListFinal.add(mapList);
                }
                lazyLists.add(mapListFinal);
            }

            
                    "shifts", Shift.values(),
                    "destination", Integer.parseInt(param("destination")),
                    "plan", Plan.findById(Integer.parseInt(param("plan"))),
                    "directions", Direction.values(),
                    "availabilitiesSubSets", lazyLists,
                    "selection", true);
        }
        */
    }

    public void availabilityConfirmation() {
        /*
        if(!negateAccess(UserType.A)) {
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

            
                    "json", jsonArray,
                    "reservation_type", param("reservation_type"),
                    "destination", param("destination"),
                    "plan", param("plan"),
                    "listOfDates", listOfDates,
                    "totalValue", totalValueOfPlanSelection.calculateTotalValue(type, plan)
            );
        }
        */
    }

    public void addReservations() {
        /*
        if(!negateAccess(UserType.A)) {
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
                        if (param(day.name()) != null && reservationJson.day == day.ordinal()) {
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

            if (PassengerPlans.find("plan_id = ? AND passenger_id = ? AND destination_id = ?",
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
                
            } else {
                
            }
            
        }
        */
    }

    public void list(){
        /*
        if(!negateAccess(UserType.P)) {
            
                    Integer.parseInt(getId())).toMaps(),
                    "days", Day.values(),
                    "shifts", Shift.values(),
                    "reservation", true);
        }
        */
    }

    public void filteredList(){
        /*
        if(!negateAccess(UserType.P)) {
            Map<String, String> map = params1st();
            Gson g = new Gson();
            JsonParser jsonParser = new JsonParser();
            jsonParser.parse(String.valueOf(map.keySet().toArray()[0])).getAsJsonObject();
            ReservationsSearchFiltersJson searchFiltersJson = g.fromJson(jsonParser.parse(
                    String.valueOf(map.keySet().toArray()[0])).getAsJsonObject(), ReservationsSearchFiltersJson.class);

            String response;

            if (searchFiltersJson.day == 0) {
                if (searchFiltersJson.shift == 0) {
                    response = ReservationInfoAgg.find("plan_id = ?", searchFiltersJson.plan_id).toJson(false);
                } else {
                    response = ReservationInfoAggShift.find("plan_id = ? AND " +
                            "shift = ?", searchFiltersJson.plan_id, searchFiltersJson.shift).toJson(false);
                }
            } else {
                if (searchFiltersJson.shift == 0) {
                    response = ReservationInfoAggDay.find("plan_id = ? AND " +
                            "day = ?", searchFiltersJson.plan_id, searchFiltersJson.day).toJson(false);
                } else {
                    response = ReservationInfoAggDayShift.find("plan_id = ? AND " +
                                    "shift = ? AND day = ?", searchFiltersJson.plan_id,
                            searchFiltersJson.shift, searchFiltersJson.day).toJson(false);
                }
            }

            respond(response).contentType("application/json").status(200);
        }
        */
    }

    public void reservationList(){
        /*
        if(!negateAccess(UserType.P, Integer.parseInt(param("passenger_id"))) || !negateAccess(UserType.A)) {
            Integer passenger_id = Integer.parseInt(param("passenger_id"));
            Integer plan_id = Integer.parseInt(param("plan_id"));
            LazyList<Reservation> reservationPAList = Reservation.find(
                    "passenger_id = ? AND plan_id = ? AND " +
                            "reservation_type = ? AND status IS TRUE",
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
            for (Reservation reservationM : reservationMList) {
                ReservationJson reservationJson = new ReservationJson(reservationM);
                reservationJsonListM.add(reservationJson);
            }
            ArrayList<ArrayList<Map<String, Object>>> listOfDatesM = new DateOfDayFinder().datesArrayList(reservationJsonListM);
            TotalValueOfPlanSelection totalValueOfReservationM = new TotalValueOfPlanSelection(listOfDatesM);


            
                    "days", Day.values(),
                    "directions", Direction.values(),
                    "reservations", ReservationInfoPassenger.find("passenger_id = ?" +
                                    " AND plan_id = ?", passenger_id,
                            plan_id),
                    "totalTicketActive", reservationPAList.size() * plan.getFloat("ticket_price"),
                    "totalTicketInactive", reservationPIList.size() * plan.getFloat("ticket_price"),
                    "totalMonthly", totalValueOfReservationM.calculateTotalValue(CalculationMethod.M, plan));
        }
        */
    }

    private Integer toInt(String s){
        return Integer.parseInt(s);
    }

    public void changeReservation(){
        /*
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
                    
                }
                else {
                    reservation.set("alteration_date", null);
                    
                }
            }
            else {
                reservation.set("status", false);
                
            }

        }
        else {
            if(isAvailabilityStillActive(reservation)) {
                if (reservation.getDate("date") == null) {
                    if (isPassengerNumberNotExcessive(reservation)) {
                        reservation.set("status", true);
                        
                    } else {
                        
                    }
                } else {
                    if (LocalDate.now().isAfter(reservation.getDate("date").toLocalDate())) {
                        
                    } else {
                        if (isPassengerNumberNotExcessive(reservation)) {
                            reservation.set("status", true);
                            
                        } else {
                            
                        }
                    }
                }
            }
            else {
                
            }
        }
        reservation.save()'
        */
        
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

    /*
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
    */

}
