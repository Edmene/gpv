package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("cities")
public class City extends Model {
    static {
        validatePresenceOf("name","state_id");
    }
}
