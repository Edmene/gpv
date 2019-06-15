package app.models;

import org.javalite.activejdbc.Model;

public class Stop extends Model {
    static {
        validatePresenceOf("time","road_id");
    }
}
