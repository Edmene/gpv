package app.controllers;

import app.controllers.authorization.Protected;
import app.enums.CalculationMethod;
import app.json.PassengerReservationsInfoJson;
import app.json.ReservationJson;
import app.models.*;
import app.utils.DateOfDayFinder;
import app.utils.Db;
import app.utils.TotalValueOfPlanSelection;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.DB;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.Types;
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

    public void getOne(@NotNull Context ctx,
                       @NotNull String planId, @NotNull String driverId,
                       @NotNull String vehicleId, @NotNull String shiftId,
                       @NotNull String dayId, @NotNull String directionId,
                       @NotNull String stopId, @NotNull String passengerId){
        try{
            Base.open(Db.getInstance());
            Reservation reservation = Reservation.findByCompositeKeys(
                    toInt(passengerId), toInt(dayId), toInt(shiftId),
                    toInt(directionId), toInt(planId), toInt(driverId),
                    toInt(vehicleId), toInt(stopId));
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

            DB db = Base.open(Db.getInstance());
            ReservationJson reservationJson  = ctx.bodyAsClass(ReservationJson.class);

            CallableStatement passengerCreationFunction = db.connection().prepareCall(
                    "{? = call create_reservation(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            passengerCreationFunction.registerOutParameter(1, Types.BOOLEAN);

            passengerCreationFunction.setInt(2, reservationJson.planId);
            passengerCreationFunction.setInt(3, Integer.parseInt(reservationJson.vehicleId));
            passengerCreationFunction.setInt(4, Integer.parseInt(reservationJson.stopId));
            passengerCreationFunction.setInt(5, reservationJson.shift);
            passengerCreationFunction.setInt(6, Integer.parseInt(reservationJson.driverId));
            passengerCreationFunction.setInt(7, reservationJson.direction);
            passengerCreationFunction.setInt(8, reservationJson.day);
            passengerCreationFunction.setInt(9, Integer.parseInt(reservationJson.passengerId));
            passengerCreationFunction.setString(10, reservationJson.reservationType.name());
            if(reservationJson.date != null) {
                passengerCreationFunction.setDate(11, Date.valueOf(reservationJson.date));
            }
            else{
                passengerCreationFunction.setDate(11, null);
            }
            passengerCreationFunction.setInt(12, reservationJson.destination);

            passengerCreationFunction.execute();

            boolean response = passengerCreationFunction.getBoolean(1);
            if (response) {
                ctx.res.setStatus(200);
            } else {
                ctx.res.setStatus(400);
                ctx.result("Invalid data received");
            }
            Base.close();

        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    public void update(@NotNull Context ctx,
                       @NotNull String planId, @NotNull String driverId,
                       @NotNull String vehicleId, @NotNull String shiftId,
                       @NotNull String dayId, @NotNull String directionId,
                       @NotNull String stopId, @NotNull String passengerId){
        try {
            Base.open(Db.getInstance());
            Reservation reservation = Reservation.findByCompositeKeys(
                    toInt(passengerId), toInt(dayId), toInt(shiftId),
                    toInt(directionId), toInt(planId), toInt(driverId),
                    toInt(vehicleId), toInt(stopId));
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

    public void delete(@NotNull Context ctx,
                       @NotNull String planId, @NotNull String driverId,
                       @NotNull String vehicleId, @NotNull String shiftId,
                       @NotNull String dayId, @NotNull String directionId,
                       @NotNull String stopId, @NotNull String passengerId){
        try{
            Base.open(Db.getInstance());
            Reservation reservation = Reservation.findByCompositeKeys(
                    toInt(passengerId), toInt(dayId), toInt(shiftId),
                    toInt(directionId), toInt(planId), toInt(driverId),
                    toInt(vehicleId), toInt(stopId));
            if(reservation != null) {
                if (reservation.delete()) {
                    ctx.res.setStatus(200);
                } else {
                    ctx.res.setStatus(400);
                }
            }
            else {
                ctx.result("Not Found");
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

    public void calculatePlanReservationValue(@NotNull Context ctx,
                                              @NotNull String planId,
                                              @NotNull String passengerId,
                                              @NotNull String planType){
        try{
            Base.open(Db.getInstance());
            LazyList<Reservation> reservations = Reservation.find("plan_id = ? AND " +
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
                    toInt(passengerId), toInt(planId), "T");

            LazyList<Reservation> reservationPIList = Reservation.find(
                    "passenger_id = ? AND plan_id = ? AND " +
                            "reservation_type = ? AND status IS FALSE",
                    toInt(passengerId), toInt(planId), "T");

            LazyList<Reservation> reservationMList = Reservation.find(
                    "passenger_id = ? AND plan_id = ? AND " +
                            "reservation_type = ? AND status IS TRUE",
                    toInt(passengerId), toInt(planId), "M");

            Plan plan = Plan.findById(toInt(planId));

            ArrayList<ReservationJson> reservationJsonListM = new ArrayList<>();
            for (Reservation reservationM : reservationMList) {
                ReservationJson reservationJson = new ReservationJson(reservationM);
                reservationJsonListM.add(reservationJson);
            }
            ArrayList<ArrayList<Map<String, Object>>> listOfDatesM = new DateOfDayFinder().datesArrayList(reservationJsonListM);
            TotalValueOfPlanSelection totalValueOfReservationM = new TotalValueOfPlanSelection(listOfDatesM);


            PassengerReservationsInfoJson item = new PassengerReservationsInfoJson();
            item.reservationInfo = ReservationInfoPassenger.find("passenger_id = ?" +
                            " AND plan_id = ?", toInt(passengerId),
                    toInt(planId)).toMaps();
            item.totalTicketActive = reservationPAList.size() * plan.getFloat("ticket_price");
            item.totalTicketInactive = reservationPIList.size() * plan.getFloat("ticket_price");
            item.signature = totalValueOfReservationM.calculateTotalValue(CalculationMethod.M, plan);


            ctx.result(mapper.writeValueAsString(item));

            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }

    }


    private Integer toInt(String s){
        return Integer.parseInt(s);
    }

}
