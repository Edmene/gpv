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

import java.util.*;

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
        LinkedList<List<Map<String, Object>>> lazyLists = new LinkedList<>();
        for(int day = 0;day < Day.values().length; day++){
            for (int shift = 0; shift < Shift.values().length; shift++){
                lazyLists.add(Availability.find("plan_id = ?" +
                                "AND shift = ? AND day = ?",
                        Integer.parseInt(param("plan")),
                        shift, day).toMaps());
            }
        }

        view("days", Day.values(),
                "shifts", Shift.values(),
                "directions", Direction.values(),
                "availabilitiesSubSets", lazyLists);
        //aqui eu cadastro ou armazeno no minimo o destination_plan.
    }
}
