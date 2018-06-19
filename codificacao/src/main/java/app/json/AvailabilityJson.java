package app.json;

import app.enums.Day;
import app.enums.Direction;
import app.enums.Shift;
import app.models.Availability;

public class AvailabilityJson {
    public String day, shift, direction;
    public Integer driver, vehicle, stop, plan;

    public AvailabilityJson(String day, String shift, String direction,
                            Integer driver, Integer vehicle, Integer stop,
                            Integer plan){
        this.day = day;
        this.shift = shift;
        this.direction = direction;
        this.driver = driver;
        this.vehicle = vehicle;
        this.stop = stop;
        this.plan = plan;
    }

    public void setAttributesOfAvailability(Availability availability){
        availability.set("day", Day.valueOf(this.day).ordinal(),
                "shift", Shift.valueOf(this.shift).ordinal(),
                "direction", Direction.valueOf(this.direction).ordinal(),
                "driver_id", this.driver,
                "vehicle_id", this.vehicle,
                "stop_id", this.stop,
                "plan_id", this.plan);
    }
}
