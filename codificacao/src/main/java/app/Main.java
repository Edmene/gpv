package app;

import app.controllers.*;

import app.models.DriverVehicleReplacement;
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;


public class Main {
    public static void main(String[] args){
        /*
            Falta availability, reservation

         */

        try {
            Javalin app = Javalin.create().enableCorsForOrigin("*").start(7000);
            app.routes(() -> {
                ApiBuilder.crud("states/:state-id", new StateController());
                ApiBuilder.crud("cities/:city-id", new CityController());
                ApiBuilder.crud("roads/:road-id", new RoadController());
                ApiBuilder.crud("destinations/:destination-id", new DestinationController());
                ApiBuilder.crud("drivers/:driver-id", new DriverController());
                ApiBuilder.crud("vehicles/:vehicle-id", new VehicleController());
                ApiBuilder.crud("driver_vehicles/:driver_vehicle-id", new DriverVehicleController());
                ApiBuilder.crud("driver_vehicle_replacements/:driver_vehicle_replacement-id", new DriverVehicleReplacementController());
                ApiBuilder.crud("active_periods/:active_period-id", new ActivePeriodController());
                ApiBuilder.crud("plans/:plan-id", new PlanController());
            });
            app.get("/cities/state/:state-id", ctx ->
                    new CityController().getByState(ctx, ctx.pathParam("state-id")));


        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}
