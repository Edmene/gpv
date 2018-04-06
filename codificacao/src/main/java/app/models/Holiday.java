package app.models;

import org.javalite.activejdbc.Model;

public class Holiday extends Model {
    static {
        validatePresenceOf("name","date");
    }
}
