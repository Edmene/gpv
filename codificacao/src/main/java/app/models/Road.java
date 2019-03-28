package app.models;

import org.javalite.activejdbc.Model;

public class Road extends Model {
    static {
        validatePresenceOf("name","city_id");
    }
}
