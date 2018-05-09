package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.CompositePK;
import org.javalite.activejdbc.annotations.Table;

@Table("passenger_plans")
@CompositePK({"passenger_id","destination_id","plan_id"})
public class PassengerPlans extends Model {
    static {
        validatePresenceOf("passenger_id","destination_id","plan_id");
    }
}
