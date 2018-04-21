package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

public class State extends Model {
    static {
        validatePresenceOf("name","acronym");
    }
}
