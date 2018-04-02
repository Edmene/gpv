package app.models;

import org.javalite.activejdbc.Model;

public class State extends Model {
    static {
        validatePresenceOf("name","acronym");
    }
}
