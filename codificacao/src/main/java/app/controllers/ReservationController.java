package app.controllers;

import app.controllers.authorization.Protected;
import app.enums.Day;
import app.enums.Direction;
import app.enums.Shift;
import app.models.Availability;
import app.models.Destination;
import app.models.DestinationPlan;
import app.models.Plan;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.annotations.POST;

import java.util.List;
import java.util.Map;

@Protected
public class ReservationController extends GenericAppController {
    @Override
    public void index() {
        view("destinations", Destination.findAll());
    }

    public void planSelection(){
        LazyList<DestinationPlan> destinationPlanLazyList = DestinationPlan.find("destination_id = ?",
                Integer.parseInt(getId())).include(Plan.class);
        List<Map<String, Object>> destinationPlanMap = destinationPlanLazyList.toMaps();

        view("destinationPlanMapList", destinationPlanMap,
                "destination", getId());
    }

    @POST
    public void availabilitySelection(){
        view("days", Day.values(),
                "shifts", Shift.values(),
                "directions", Direction.values(),
                "availabilities", Availability.find("plan_id = ?",
                        Integer.parseInt(param("plan"))));
        //aqui eu cadastro ou armazeno no minimo o destination_plan.
    }
}
