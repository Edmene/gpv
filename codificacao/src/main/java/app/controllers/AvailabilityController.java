package app.controllers;

import app.enums.Day;
import app.enums.Shift;
import app.models.Availability;
import app.models.Driver;
import app.models.Stop;
import app.models.Vehicle;
import org.javalite.activeweb.annotations.POST;

public class AvailabilityController extends GenericAppController {

    public void plan(){
        view("availabilities", Availability.find("plan_id = ?",
                Integer.parseInt(getId())).toMaps(), "plan", getId());
    }

    @POST
    public void addStop(){
        view("stops", Stop.findAll(),
                "plan", param("plan"),
                "vehicle", param("vehicle"),
                "driver", param("driver"),
                "shift", param("shift"),
                "day", param("day"));
    }

    @POST
    public void addStops(){
        String[] availabilities = param("items").split(",");
        for (String availabilityItem : availabilities) {
            Availability availability = new Availability();
            availability.set("destination_id", Integer.parseInt(availabilityItem),
                    "plan_id", Integer.parseInt(param("plan")));
            availability.insert();
        }
        redirect(PlanController.class);
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
