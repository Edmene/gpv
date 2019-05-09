package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.CompositePK;
import org.javalite.activejdbc.annotations.Table;

@Table("active_period_plans")
@CompositePK({ "active_period_id", "plan_id" })
public class ActivePeriodPlan extends Model {
    static {
        validatePresenceOf("active_period_id","plan_id")
                .message("Falta um ou mais chaves primárias");
    }
}
