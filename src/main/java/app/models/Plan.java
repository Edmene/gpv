package app.models;

import org.javalite.activejdbc.Model;

public class Plan extends Model {
    static {
        validatePresenceOf("availability_condition","daily_value",
                "ticket_price","available_reservations","destination_id");
    }
}
