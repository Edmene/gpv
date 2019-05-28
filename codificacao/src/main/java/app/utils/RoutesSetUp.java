package app.utils;

import app.controllers.*;
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;

public class RoutesSetUp {

    public static void defineRoutes(Javalin app){
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

        defineComplexRoutes(app);

    }

    private static void defineComplexRoutes(Javalin app){
        app.get("/cities/state/:state-id", ctx ->
                new CityController().getByState(ctx, ctx.pathParam("state-id")));

        //Availability

        app.get("/availability", ctx -> new AvailabilityController().getAll(ctx));

        app.get("/availability/:plan-id", ctx -> new AvailabilityController()
                .availabilitiesOfPlanWithDriverAndVehicle(ctx, ctx.pathParam("plan-id")));

        app.post("/availability", ctx -> new AvailabilityController().create(ctx));

        app.get("/availability/:plan-id/driver/:driver-id/vehicle/" +
                ":vehicle-id/shift/:shift-id/day/:day-id" +
                "/direction/:direction-id/stop/:stop-id", ctx -> new AvailabilityController().getOne(ctx,
                ctx.pathParam("plan-id"), ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id"),
                ctx.pathParam("shift-id"), ctx.pathParam("day-id"), ctx.pathParam("direction-id"),
                ctx.pathParam("stop-id")));

        app.patch("/availability/:plan-id/driver/:driver-id/vehicle/" +
                ":vehicle-id/shift/:shift-id/day/:day-id" +
                "/direction/:direction-id/stop/:stop-id", ctx -> new AvailabilityController().getOne(ctx,
                ctx.pathParam("plan-id"), ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id"),
                ctx.pathParam("shift-id"), ctx.pathParam("day-id"), ctx.pathParam("direction-id"),
                ctx.pathParam("stop-id")));

        app.delete("/availability/:plan-id/driver/:driver-id/vehicle/" +
                ":vehicle-id/shift/:shift-id/day/:day-id" +
                "/direction/:direction-id/stop/:stop-id", ctx -> new AvailabilityController().getOne(ctx,
                ctx.pathParam("plan-id"), ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id"),
                ctx.pathParam("shift-id"), ctx.pathParam("day-id"), ctx.pathParam("direction-id"),
                ctx.pathParam("stop-id")));

        app.get("/reservation/:plan-id/driver/:driver-id/vehicle/" +
                ":vehicle-id/shift/:shift-id/day/:day-id" +
                "/direction/:direction-id/stop/:stop-id" +
                "/passenger/:passenger-id", ctx -> new ReservationController().getOne(ctx,
                ctx.pathParam("plan-id"), ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id"),
                ctx.pathParam("shift-id"), ctx.pathParam("day-id"), ctx.pathParam("direction-id"),
                ctx.pathParam("stop-id"), ctx.pathParam("passenger-id")));
        ///

        //Reservation

        app.get("/reservation", ctx -> new AvailabilityController().getAll(ctx));

        app.post("/reservation", ctx -> new AvailabilityController().create(ctx));

        app.patch("/reservation/:plan-id/driver/:driver-id/vehicle/" +
                ":vehicle-id/shift/:shift-id/day/:day-id" +
                "/direction/:direction-id/stop/:stop-id" +
                "/passenger/:passenger-id", ctx -> new ReservationController().getOne(ctx,
                ctx.pathParam("plan-id"), ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id"),
                ctx.pathParam("shift-id"), ctx.pathParam("day-id"), ctx.pathParam("direction-id"),
                ctx.pathParam("stop-id"), ctx.pathParam("passenger-id")));

        app.delete("/reservation/:plan-id/driver/:driver-id/vehicle/" +
                ":vehicle-id/shift/:shift-id/day/:day-id" +
                "/direction/:direction-id/stop/:stop-id" +
                "/passenger/:passenger-id", ctx -> new ReservationController().getOne(ctx,
                ctx.pathParam("plan-id"), ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id"),
                ctx.pathParam("shift-id"), ctx.pathParam("day-id"), ctx.pathParam("direction-id"),
                ctx.pathParam("stop-id"), ctx.pathParam("passenger-id")));
        ///
    }
}
