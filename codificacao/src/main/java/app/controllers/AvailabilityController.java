package app.controllers;

import app.enums.Day;
import app.enums.Shift;
import app.models.Availability;
import app.models.Driver;
import app.models.Stop;
import app.models.Vehicle;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;

public class AvailabilityController extends GenericAppController {

    public void plan(){
        view("availabilities", Availability.find("plan_id = ?",
                Integer.parseInt(getId())).toMaps(), "plan", getId(),
                "days", Day.values(), "shifts", Shift.values());
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
        LazyList availabilities = Availability.find(
                "day = ? AND shift = ?",
                Day.valueOf(param("day")).ordinal(),
                Shift.valueOf(param("shift")).ordinal());
        Boolean allowAddition = true;
        if(availabilities.size() != 0){
            for(Object object : availabilities){
                Availability availability = (Availability) object;
                if(availability.getInteger("driver_id") == Integer.parseInt(param("driver")) ||
                    availability.getInteger("vehicle_id") == Integer.parseInt(param("vehicle_id"))){
                    flash("message", "Não é permitido alocar um motorista ou " +
                            "veiculo que conflite com uma disponibilidade existente de outro plano");
                    allowAddition = false;
                    redirect(PlanController.class);
                }
            }
        }
        if(allowAddition) {
            String[] stops = param("items").split(",");
            for (String stop : stops) {
                Availability availability = new Availability();
                availability.set(
                        "day", Day.valueOf(param("day")).ordinal(),
                        "shift", Shift.valueOf(param("shift")).ordinal(),
                        "plan_id", Integer.parseInt(param("plan")),
                        "driver_id", Integer.parseInt(param("driver")),
                        "vehicle_id", Integer.parseInt(param("vehicle")),
                        "stop_id", Integer.parseInt(stop));
                availability.insert();
            }
            redirect(PlanController.class);
        }
    }

    @Override
    public void newForm(){
        view("drivers", Driver.findAll().toMaps(),
                "vehicles", Vehicle.findAll().toMaps(),
                "plan", getId(),
                "days", Day.values(),
                "shifts", Shift.values());
    }

    @Override @DELETE
    public void delete() {
        Availability availability = new Availability();
        availability.set(
                "day", Integer.parseInt(param("day")),
                "shift", Integer.parseInt(param("shift")),
                "plan_id", Integer.parseInt(param("plan_id")),
                "driver_id", Integer.parseInt(param("driver_id")),
                "vehicle_id", Integer.parseInt(param("vehicle_id")),
                "stop_id", Integer.parseInt(param("stop_id"))
        );
        availability.delete();
        flash("message", "A disponibilidae do plano foi deletada");
        redirect(PlanController.class);
    }
}
