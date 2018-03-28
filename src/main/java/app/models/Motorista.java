package app.models;

import org.javalite.activejdbc.Model;

public class Motorista extends Model {
    static {
        validatePresenceOf("nome","sobrenome","rg","cnh");
    }
}
