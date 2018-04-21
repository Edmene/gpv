package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

public class City extends Model {
    static {
        validatePresenceOf("name","state_id");
    }
}
