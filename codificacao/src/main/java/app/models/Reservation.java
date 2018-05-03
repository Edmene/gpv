package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.CompositePK;

@CompositePK({"passenger_id", "day", "shift", "direction", "plan_id", "driver_id", "vehicle_id", "stop_id" })
public class Reservation extends Model {
    static {
        validatePresenceOf("reservation_type","status",
                "passenger_id","day","shift","direction","plan_id","driver_id",
                "vehicle_id","stop_id");
    }
}
