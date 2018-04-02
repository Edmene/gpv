package app.models;

import org.javalite.activejdbc.Model;

public class Vehicle extends Model {
    static {
        validatePresenceOf("capacity","license_plate","model","year");
    }
}
