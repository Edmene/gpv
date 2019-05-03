package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.CompositePK;
import org.javalite.activejdbc.annotations.Table;

@Table("driver_vehicles")
@CompositePK({ "driver_id", "vehicle_id" })
public class DriverVehicle extends Model {
    static {
        validatePresenceOf("driver_id", "vehicle_id")
                .message("Falta um ou mais chaves primárias");
    }
}
