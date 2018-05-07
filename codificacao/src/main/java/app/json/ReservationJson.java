package app.json;

import app.models.Reservation;

public class ReservationJson {
    public Integer day,shift,direction;
    public String driverId,vehicleId,stopId;

    public ReservationJson(Integer day, Integer shift, Integer direction, String driverId,
                       String vehicleId, String stopId){
        this.day = day;
        this.shift = shift;
        this.direction = direction;
        this.driverId = driverId;
        this.vehicleId = vehicleId;
        this.stopId = stopId;
    }

    public ReservationJson(Reservation reservation){
        this.day = reservation.getInteger("day");
        this.shift = reservation.getInteger("shift");
        this.direction = reservation.getInteger("direction");
        this.driverId = reservation.get("driver_id").toString();
        this.vehicleId = reservation.get("vehicle_id").toString();
        this.stopId = reservation.get("stop_id").toString();
    }

    public void setAttributesOfReservation(Reservation reservation){
        reservation.set("day", this.day,
                "shift", this.shift,
                "direction", this.direction,
                "driver_id", Integer.parseInt(this.driverId),
                "vehicle_id", Integer.parseInt(this.vehicleId),
                "stop_id", Integer.parseInt(this.stopId));
    }
}
