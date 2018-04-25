package app.controllers;

import app.enums.Day;
import app.enums.Shift;
import app.models.Availability;
import app.models.Driver;
import app.models.Vehicle;

public class AvailabilityController extends GenericAppController {

    public void plan(){
        view("availabilities", Availability.find("plan_id = ?",
                Integer.parseInt(getId())).toMaps(), "plan", getId());
    }

    @Override
    public void newForm(){
        view("drivers", Driver.findAll().toMaps(),
                "vehicles", Vehicle.findAll().toMaps(),
                "plan", getId(),
                "days", Day.values(),
                "shifts", Shift.values());
    }
}
