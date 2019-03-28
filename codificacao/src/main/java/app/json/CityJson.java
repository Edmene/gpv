package app.json;

import app.models.City;
import app.models.State;

public class CityJson {
    public Integer key, stateKey;
    public String name;

    public CityJson(){}

    public CityJson(String name, Integer stateKey){
        this.name = name;
        this.stateKey = stateKey;
    }

    public CityJson(City city){
        this.key = (Integer) city.getId();
        this.name = city.getString("name");
        this.stateKey = city.getInteger("state_id");
    }

    public void setAttributesOfCity(City city){
        city.set("id", this.key,
                "name", this.name,
                "state_id", this.stateKey);
    }
}
