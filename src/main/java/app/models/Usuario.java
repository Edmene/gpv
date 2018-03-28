package app.models;

import org.javalite.activejdbc.Model;

public class Usuario extends Model {
    static {
        validatePresenceOf("nome", "senha");
    }
}
