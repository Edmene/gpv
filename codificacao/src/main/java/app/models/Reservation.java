package app.models;

import org.javalite.activejdbc.Model;

public class Reservation extends Model {

    public Integer day,shift,direction,
    driverId,vehicleId,stopId;

    public Reservation(Integer day, Integer shift, Integer direction, Integer driverId,
                       Integer vehicleId, Integer stopId){
        this.day = day;
        this.shift = shift;
        this.direction = direction;
        this.driverId = driverId;
        this.vehicleId = vehicleId;
        this.stopId = stopId;
        setAttributesOfReservation();
    }

    private void setAttributesOfReservation(){
        this.set("day", this.day,
                "shift", this.day,
                "direction", this.direction,
                "driver_id", this.driverId,
                "vehicle_id", this.vehicleId,
                "stop_id", this.stopId);
    }

    static {
        validatePresenceOf("reservation_type","status",
                "passenger_id","day","shift","direction","plan_id","driver_id",
                "vehicle_id","stop_id");
    }
}
