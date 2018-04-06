package app.models;

import org.javalite.activejdbc.Model;

public class Passenger extends Model {
    static {
        validatePresenceOf("name","surname","cpf","rg","birth_date",
                "telephone","email");
    }
}
