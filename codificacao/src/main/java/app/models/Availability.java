package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.CompositePK;
import org.javalite.activejdbc.annotations.Table;

@CompositePK({ "day", "shift", "plan_id", "driver_id", "vehicle_id", "stop_id" })
public class Availability extends Model {
    static {
        validatePresenceOf("day","shift","plan_id","driver_id","vehicle_id","stop_id");
    }
}
