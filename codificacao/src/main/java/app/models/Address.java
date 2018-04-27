package app.models;

import org.javalite.activejdbc.Model;

public class Address extends Model {
    static {
        validatePresenceOf("name","city_id");
    }
}
