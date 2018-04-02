package app.models;

import org.javalite.activejdbc.Model;

public class Driver extends Model {
    static {
        validatePresenceOf("name","surname","rg","cnh");
    }
}
