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

    public DestinationJson(Destination Destination){
        this.key = (Integer) Destination.getId();
        this.name = Destination.getString("name");
        this.roadKey = Destination.getInteger("road_id");
        this.addressNumber = Destination.getString("address_number");
    }

    public void setAttributesOfDestination(Destination Destination){
        Destination.set("id", this.key,
                "name", this.name,
                "road_id", this.roadKey,
                "address_number", this.addressNumber);
    }
}
