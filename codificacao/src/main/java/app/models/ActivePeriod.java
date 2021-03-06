package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.CompositePK;
import org.javalite.activejdbc.annotations.Table;

@Table("active_periods")
@CompositePK({"id", "plan_id"})
public class ActivePeriod extends Model {
    static {
        validatePresenceOf("initial_date","final_date", "plan_id");
    }
}
