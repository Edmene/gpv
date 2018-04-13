package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.CompositePK;
import org.javalite.activejdbc.annotations.Table;

@Table("destination_plans")
@CompositePK({ "destination_id", "plan_id" })
public class DestinationPlan extends Model {
    static {
        validatePresenceOf("destination_id","plan_id")
                .message("Falta um ou mais chaves primárias");
    }
}
