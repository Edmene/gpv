package app.json;

import app.enums.Day;
import app.enums.Direction;
import app.enums.Shift;
import app.models.Availability;
import app.utils.DayDeserializer;
import app.utils.DaySerializer;
import app.utils.ShiftDeserializer;
import app.utils.ShiftSerializer;
import app.utils.DirectionDeserializer;
import app.utils.DirectionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class AvailabilityJson {
    @JsonSerialize(using = DaySerializer.class)
    @JsonDeserialize(using = DayDeserializer.class)
    public Day day;
    @JsonSerialize(using = ShiftSerializer.class)
    @JsonDeserialize(using = ShiftDeserializer.class)
    public Shift shift;
    @JsonSerialize(using = DirectionSerializer.class)
    @JsonDeserialize(using = DirectionDeserializer.class)
    public Direction direction;
    public Integer driver, vehicle, stop, plan;
    public boolean status;

    public AvailabilityJson(){}

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
        this.driver = availability.getInteger("driver_id");
        this.vehicle = availability.getInteger("vehicle_id");
        this.stop = availability.getInteger("stop_id");
        this.plan = availability.getInteger("plan_id");
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
