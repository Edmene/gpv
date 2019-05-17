package app.json;

import app.enums.Day;
import app.enums.Direction;
import app.enums.Shift;
import app.models.Availability;

public class AvailabilityJson {
    public Day day;
    public Shift shift;
    public Direction direction;
    public Integer driver, vehicle, stop, plan;
    public boolean status;

    public AvailabilityJson(Day day, Shift shift, Direction direction,
                            Integer driver, Integer vehicle, Integer stop,
                            Integer plan, Boolean status){
        this.day = day;
        this.shift = shift;
        this.direction = direction;
        this.driver = driver;
        this.vehicle = vehicle;
        this.stop = stop;
        this.plan = plan;
        this.status = status;
    }

    public AvailabilityJson(Availability availability){
        this.day = Day.values()[availability.getInteger("day")];
        this.shift = Shift.values()[availability.getInteger("shift")];
        this.direction = Direction.values()[availability.getInteger("direction")];
        this.driver = availability.getInteger("driver");
        this.vehicle = availability.getInteger("vehicle");
        this.stop = availability.getInteger("stop");
        this.plan = availability.getInteger("plan");
        this.status = availability.getBoolean("status");
    }

    public void setAttributesOfAvailability(Availability availability){
        availability.set("day", this.day.ordinal(),
                "shift", this.shift.ordinal(),
                "direction", this.direction.ordinal(),
                "driver_id", this.driver,
                "vehicle_id", this.vehicle,
                "stop_id", this.stop,
                "plan_id", this.plan,
                "status", this.status);
    }
}
