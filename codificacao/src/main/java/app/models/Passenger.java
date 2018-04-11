package app.models;

import org.javalite.activejdbc.Model;
//import org.javalite.activejdbc.annotations.BelongsTo;

//@BelongsTo(foreignKeyName = "id", parent = User.class)
public class Passenger extends Model {
    static {
        validatePresenceOf("user_id","name","surname","cpf","rg","birth_date",
                "telephone","email");
        validateEmailOf("email");
    }
}
