package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

public class Availability extends Model {
    static {
        validatePresenceOf("day","shift","plan_id","driver_id","vehicle_id");
    }
}
