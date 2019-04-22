package app.json;

import app.models.Destination;

public class DestinationJson {
    public Integer key, roadKey;
    public String name, addressNumber;

    public DestinationJson(){}

    public DestinationJson(String name, String addressNumber, Integer roadKey){
        this.name = name;
        this.addressNumber = addressNumber;
        this.roadKey = roadKey;
    }

    public DestinationJson(Destination destination){
        this.key = (Integer) destination.getId();
        this.name = destination.getString("name");
        this.roadKey = destination.getInteger("road_id");
        this.addressNumber = destination.getString("address_number");
    }

    public void setAttributesOfDestination(Destination destination){
        destination.set("id", this.key,
                "name", this.name,
                "road_id", this.roadKey,
                "address_number", this.addressNumber);
    }
}
