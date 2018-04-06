package app.models;

import org.javalite.activejdbc.Model;

public class Destination extends Model {
    static {
        validatePresenceOf("name","address_id");
    }
}
