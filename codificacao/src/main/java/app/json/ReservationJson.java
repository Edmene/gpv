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

    public void setAttributesOfReservation(Reservation reservation){
        reservation.set("day", this.day,
                "shift", this.shift,
                "direction", this.direction,
                "driver_id", Integer.parseInt(this.driverId),
                "vehicle_id", Integer.parseInt(this.vehicleId),
                "stop_id", Integer.parseInt(this.stopId));
    }
}
