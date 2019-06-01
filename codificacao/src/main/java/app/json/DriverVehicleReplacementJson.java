package app.json;

import app.models.DriverVehicleReplacement;

import java.sql.Date;
import java.time.LocalDate;

public class DriverVehicleReplacementJson {
    public Integer driverKey, vehicleKey,
            driverVKey,vehicleVKey;
    public LocalDate date;

    public DriverVehicleReplacementJson(){}

    public DriverVehicleReplacementJson(Integer driverKey, Integer vehicleKey,
                                        Integer driverVKey, Integer vehicleVKey,
                                        LocalDate date){
        this.driverKey = driverKey;
        this.vehicleKey = vehicleKey;
        this.driverVKey = driverVKey;
        this.vehicleVKey = vehicleVKey;
        this.date = date;
    }

    public DriverVehicleReplacementJson(DriverVehicleReplacement driverVehicleReplacement){
        this.driverKey = driverVehicleReplacement.getInteger("driver_id");
        this.vehicleKey = driverVehicleReplacement.getInteger("vehicle_id");
        this.driverVKey = driverVehicleReplacement.getInteger("driver_v_id");
        this.vehicleVKey = driverVehicleReplacement.getInteger("vehicle_v_id");
        this.date = LocalDate.from(driverVehicleReplacement.getDate("date").toInstant());
    }

    public void setAttributesOfDriverVehicleReplacement(DriverVehicleReplacement driverVehicleReplacement){
        driverVehicleReplacement.set("driver_id", this.driverKey,
                "vehicle_id", this.vehicleKey,
                "driver_v_id", this.driverVKey,
                "vehicle_v_id", this.vehicleVKey,
                "date", Date.valueOf(this.date));
    }
}