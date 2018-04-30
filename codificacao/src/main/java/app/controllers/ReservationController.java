package app.controllers;

import app.controllers.authorization.Protected;
import app.models.Destination;
import app.models.DestinationPlan;
import app.models.Plan;
import org.javalite.activejdbc.LazyList;

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

        view("destinationPlanMapList", destinationPlanMap);
    }

    public void availabilitySelection(){
        //aqui eu cadastro ou armazeno no minimo o destination_plan.
    }
}
