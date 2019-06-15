package app.json;

import app.enums.CalculationMethod;
import app.models.Reservation;
import app.utils.CalcMethodDeserializer;
import app.utils.CalcMethodSerializer;
import app.utils.JavaScriptDateDeserializer;
import app.utils.JavaScriptDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Date;
import java.time.LocalDate;

public class ReservationJson {
    public Integer day,shift,direction, planId;
    public String driverId,vehicleId,stopId,passengerId;
    @JsonSerialize(using = CalcMethodSerializer.class)
    @JsonDeserialize(using = CalcMethodDeserializer.class)
    public CalculationMethod reservationType;
    public Boolean status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    @JsonSerialize(using = JavaScriptDateSerializer.class)
    @JsonDeserialize(using = JavaScriptDateDeserializer.class)
    public LocalDate date, alterationDate;

    public Integer destination;

    public ReservationJson(){}

    public ReservationJson(Integer day, Integer shift, Integer direction, String driverId,
                           String vehicleId, String stopId, String passengerId, Integer planId,
                           CalculationMethod reservationType, Boolean status, LocalDate date,
                           LocalDate alterationDate, Integer destination){
        this.day = day;
        this.shift = shift;
        this.direction = direction;
        this.planId = planId;
        this.driverId = driverId;
        this.vehicleId = vehicleId;
        this.stopId = stopId;
        this.passengerId = passengerId;
        this.reservationType = reservationType;
        this.status = status;
        this.date = date;
        this.alterationDate = alterationDate;
        this.destination = destination;
    }

    public ReservationJson(Reservation reservation){
        this.day = reservation.getInteger("day");
        this.shift = reservation.getInteger("shift");
        this.direction = reservation.getInteger("direction");
        this.planId = reservation.getInteger("plan_id");
        this.driverId = reservation.get("driver_id").toString();
        this.vehicleId = reservation.get("vehicle_id").toString();
        this.stopId = reservation.get("stop_id").toString();
        this.passengerId = reservation.get("passenger_id").toString();
        this.reservationType = CalculationMethod.valueOf(reservation.getString("reservation_type"));
        this.status = reservation.getBoolean("status");
        if(reservation.getDate("date") != null) {
            this.date = reservation.getDate("date").toLocalDate();
        }
        if(reservation.getDate("alteration_date") != null) {
            this.alterationDate = reservation.getDate("alteration_date").toLocalDate();
        }
    }

    public void setAttributesOfReservation(Reservation reservation){
        reservation.set("day", this.day,
                "shift", this.shift,
                "direction", this.direction,
                "driver_id", Integer.parseInt(this.driverId),
                "vehicle_id", Integer.parseInt(this.vehicleId),
                "stop_id", Integer.parseInt(this.stopId),
                "plan_id", this.planId,
                "passenger_id", Integer.parseInt(this.passengerId),
                "reservation_type", this.reservationType.name(),
                "status", this.status);
        if(this.date != null){
            reservation.set("date", Date.valueOf(this.date));
        }
        if(this.alterationDate != null){
            reservation.set("alteration_date", Date.valueOf(this.alterationDate));
        }
    }
}
