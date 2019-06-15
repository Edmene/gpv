package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.CompositePK;
import org.javalite.activejdbc.annotations.Table;

@Table("driver_vehicle_replacements")
@CompositePK({ "driver_id", "vehicle_id", "driver_v_id",
        "vehicle_v_id", "date" })
public class DriverVehicleReplacement extends Model {
    static {
        validatePresenceOf("driver_id", "vehicle_id",
                "driver_v_id", "vehicle_v_id", "date")
                .message("Falta um ou mais chaves primárias");
    }
}
