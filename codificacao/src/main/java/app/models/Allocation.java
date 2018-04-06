package app.models;

import org.javalite.activejdbc.Model;

public class Allocation extends Model {
    static {
        validatePresenceOf("driver_id","id","shift","day","plan_id");
    }
}
