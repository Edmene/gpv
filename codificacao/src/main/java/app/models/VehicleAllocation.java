package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("vehicle_allocations")
public class VehicleAllocation extends Model {
    static {
        validatePresenceOf("vehicle_id","allocation_id");
    }
}
