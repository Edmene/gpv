package app.json;

import app.models.DriverVehicleReplacement;
import app.utils.JavaScriptDateDeserializer;
import app.utils.JavaScriptDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Date;
import java.time.LocalDate;

public class DriverVehicleReplacementJson {
    public Integer driverKey, vehicleKey,
            driverVKey,vehicleVKey;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    @JsonSerialize(using = JavaScriptDateSerializer.class)
    @JsonDeserialize(using = JavaScriptDateDeserializer.class)
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
        this.date = driverVehicleReplacement.getDate("date").toLocalDate();
    }

    public void setAttributesOfDriverVehicleReplacement(DriverVehicleReplacement driverVehicleReplacement){
        driverVehicleReplacement.set("driver_id", this.driverKey,
                "vehicle_id", this.vehicleKey,
                "driver_v_id", this.driverVKey,
                "vehicle_v_id", this.vehicleVKey,
                "date", Date.valueOf(this.date));
    }
}
