package app.controllers;

import app.controllers.authorization.Protected;
import app.enums.CalculationMethod;
import app.json.ReservationJson;
import app.models.*;
import app.utils.DateOfDayFinder;
import app.utils.Db;
import app.utils.TotalValueOfPlanSelection;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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

    private boolean isEqualAvailability(Reservation reservation, ReservationJson reservationJson){
        return (reservation.getInteger("passenger_id") == Integer.parseInt(reservationJson.passengerId) &&
                reservation.getInteger("day") == reservationJson.day &&
                reservation.getInteger("shift") == reservationJson.shift &&
                reservation.getInteger("direction") == reservationJson.direction &&
                reservation.getInteger("driver_id") == Integer.parseInt(reservationJson.driverId) &&
                reservation.getInteger("vehicle_id") == Integer.parseInt(reservationJson.vehicleId) &&
                reservation.getInteger("stop_id") == Integer.parseInt(reservationJson.stopId) &&
                reservation.getInteger("plan_id") == reservationJson.planId);
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
                ctx.res.setStatus(200);
                ReservationJson reservationJson = new ReservationJson(reservation);
                ctx.result(mapper.writeValueAsString(reservationJson));
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
            if(reservation == null){
                ctx.res.setStatus(404);
            }
            if(isEqualAvailability(reservation, reservationJson)){
                ctx.res.setStatus(400);
                return;
            }
            else {
                reservationJson.setAttributesOfReservation(reservation);
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

    public void calculatePlanReservationValue(@NotNull Context ctx,
                                              @NotNull String planId,
                                              @NotNull String passengerId,
                                              @NotNull String planType){
        try{
            Base.open(Db.getInstance());
            LazyList<Reservation> reservations = Reservation.find("plan_id = ? AND" +
                    "passenger_id = ? AND reservation_type = ? AND status = true",
                    Integer.parseInt(planId), Integer.parseInt(passengerId), planType.charAt(0));
            ArrayList<ReservationJson> reservationJsons = new ArrayList<>();
            for (Reservation reservation: reservations) {
                reservationJsons.add(new ReservationJson(reservation));
            }
            if(!reservations.isEmpty()){
                DateOfDayFinder ddf = new DateOfDayFinder();
                TotalValueOfPlanSelection tvps = new TotalValueOfPlanSelection(ddf.datesArrayList(reservationJsons));
                ctx.result(
                        mapper.writeValueAsString(
                                tvps.calculateTotalValue(CalculationMethod.valueOf(planType),
                                        Plan.findById(Integer.parseInt(planId)))
                        )
                );
            }
            else{
                ctx.res.setStatus(404);
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }

    }


    public void reservationList(@NotNull Context ctx,
                                @NotNull String planId,
                                @NotNull String passengerId){
        try{
            Base.open(Db.getInstance());

            LazyList<Reservation> reservationPAList = Reservation.find(
                    "passenger_id = ? AND plan_id = ? AND " +
                            "reservation_type = ? AND status IS TRUE",
                    passengerId, planId, "T");

            LazyList<Reservation> reservationPIList = Reservation.find(
                    "passenger_id = ? AND plan_id = ? AND " +
                            "reservation_type = ? AND status IS FALSE",
                    passengerId, planId, "T");

            LazyList<Reservation> reservationMList = Reservation.find(
                    "passenger_id = ? AND plan_id = ? AND " +
                            "reservation_type = ? AND status IS TRUE",
                    passengerId, planId, "M");

            Plan plan = Plan.findById(planId);

            ArrayList<ReservationJson> reservationJsonListM = new ArrayList<>();
            for (Reservation reservationM : reservationMList) {
                ReservationJson reservationJson = new ReservationJson(reservationM);
                reservationJsonListM.add(reservationJson);
            }
            ArrayList<ArrayList<Map<String, Object>>> listOfDatesM = new DateOfDayFinder().datesArrayList(reservationJsonListM);
            TotalValueOfPlanSelection totalValueOfReservationM = new TotalValueOfPlanSelection(listOfDatesM);

            ArrayList<Object> arrayResult = new ArrayList<>();
            arrayResult.add(ReservationInfoPassenger.find("passenger_id = ?" +
                            " AND plan_id = ?", passengerId,
                    planId).toMaps());
            arrayResult.add(reservationPAList.size() * plan.getFloat("ticket_price"));
            arrayResult.add(reservationPIList.size() * plan.getFloat("ticket_price"));
            arrayResult.add(totalValueOfReservationM.calculateTotalValue(CalculationMethod.M, plan));

            mapper.writeValueAsString(arrayResult);

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

    public void addReservations() {
        /*

        -> FUNCIONALIDADE PASSA PARA UMA TRIGGER ATIVADA POR UMA ADICAO DE RESERVA

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

    private Integer toInt(String s){
        return Integer.parseInt(s);
    }

    public void changeReservation(){
        /*

        -> Vira um trigger ativado pela delecao na base de dados

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

        -> Forte candidato a se tornar uma trigger.

        VERIFICA CONFLITOS COM OUTRAS RESERVAS


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
