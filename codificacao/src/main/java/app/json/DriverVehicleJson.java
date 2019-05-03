package app.json;

import app.models.DriverVehicle;
import app.models.Vehicle;

public class DriverVehicleJson {
    public Integer driverKey, vehicleKey;

    public DriverVehicleJson(){}

    public DriverVehicleJson(Integer driverKey, Integer vehicleKey){
        this.driverKey = driverKey;
        this.vehicleKey = vehicleKey;
    }

    public DriverVehicleJson(DriverVehicle driverVehicle){
        this.driverKey = driverVehicle.getInteger("driver_id");
        this.vehicleKey = driverVehicle.getInteger("vehicle_id");
    }

    public void setAttributesOfDriverVehicle(DriverVehicle driverVehicle){
        driverVehicle.set("driver_id", this.driverKey,
                "vehicle_id", this.vehicleKey);
    }
}
