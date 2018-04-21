package app.models;

import org.javalite.activejdbc.Model;

public class Reservation extends Model {
    static {
        validatePresenceOf("reservation_type","status",
                "passenger_id","day","shift","plan_id","driver_id",
                "vehicle_id","stop_id");
    }
}
