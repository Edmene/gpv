package app.models;

import org.javalite.activejdbc.Model;

public class City extends Model {
    static {
        validatePresenceOf("name","state_id");
    }
}
