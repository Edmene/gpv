package app.json;

import app.models.Road;

public class RoadJson {
    public Integer key, cityKey;
    public String name;

    public RoadJson(){}

    public RoadJson(String name, Integer cityKey){
        this.name = name;
        this.cityKey = cityKey;
    }

    public RoadJson(Road road){
        this.key = (Integer) road.getId();
        this.name = road.getString("name");
        this.cityKey = road.getInteger("city_id");
    }

    public void setAttributesOfRoad(Road road){
        road.set("id", this.key,
                "name", this.name,
                "city_id", this.cityKey);
    }
}
