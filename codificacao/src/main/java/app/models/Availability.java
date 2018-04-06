package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("availabilities")
public class Availability extends Model {
    static {
        validatePresenceOf("shift","day","plan_id");
    }
}
