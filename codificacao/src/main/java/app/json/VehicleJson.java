package app.json;

import app.models.Vehicle;

public class VehicleJson {
    public Integer key, capacity, year;
    public String model, licensePlate;

    public VehicleJson(){}

    public VehicleJson(String model, String licensePlate, Integer capacity, Integer year){
        this.model = model;
        this.licensePlate = licensePlate;
        this.capacity = capacity;
        this.year = year;
    }

    public VehicleJson(Vehicle vehicle){
        this.key = (Integer) vehicle.getId();
        this.model = vehicle.getString("model");
        this.licensePlate = vehicle.getString("licensePlate");
        this.capacity = vehicle.getInteger("capacity");
        this.year = vehicle.getInteger("year");
    }

    public void setAttributesOfVehicle(Vehicle vehicle){
        vehicle.set("id", this.key,
                "model", this.model,
                "licensePlate", this.licensePlate,
                "capacity", this.capacity,
                "year", this.year);
    }
}
