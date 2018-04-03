package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("states")
public class State extends Model {
    static {
        validatePresenceOf("name","acronym");
    }
}
