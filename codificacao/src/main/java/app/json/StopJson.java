package app.json;

import app.models.Stop;

import java.sql.Time;
import java.time.LocalTime;

public class StopJson {
    public Integer key, roadKey;
    public String addressNumber;
    public LocalTime time;

    public StopJson(){}

    public StopJson(String addressNumber, Integer roadKey, LocalTime time){
        this.addressNumber = addressNumber;
        this.roadKey = roadKey;
        this.time = time;
    }

    public StopJson(Stop stop){
        this.key = (Integer) stop.getId();
        this.addressNumber = stop.getString("address_number");
        this.roadKey = stop.getInteger("road_id");
        this.time = LocalTime.from(stop.getTime("time").toInstant());
    }

    public void setAttributesOfStop(Stop stop){
        stop.set("id", this.key,
                "address_number", this.addressNumber,
                "road_id", this.roadKey,
                "time", Time.valueOf(this.time));
    }
}
