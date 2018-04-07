package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("destination_plans")
public class DestinationPlan extends Model {
    static {
        validatePresenceOf("destination_id","plan_id");
    }
}
