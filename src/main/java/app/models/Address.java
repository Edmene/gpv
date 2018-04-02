package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("addresses")
public class Address extends Model {
    static {
        validatePresenceOf("name","city_id");
    }
}
