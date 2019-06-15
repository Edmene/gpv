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
            ApiBuilder.crud("active_periods/:active_period-id", new ActivePeriodController());
            ApiBuilder.crud("plans/:plan-id", new PlanController());
            ApiBuilder.crud("passengers/:passenger-id", new PassengerController());
            ApiBuilder.crud("users/:user-id", new UserController());
        });

        defineComplexRoutes(app);

    }

    private static void defineComplexRoutes(Javalin app){

            cityRoutes(app);
            stopRoutes(app);
            passengerRoutes(app);
            destinationRoutes(app);
            planRoutes(app);
            availabilityRoutes(app);
            reservationRoutes(app);
            driverVehicleRoutes(app);
            driverVehicleReplacementRoutes(app);

    }
    
    private static void driverVehicleRoutes(Javalin app){

        app.get("/driver-vehicles", ctx -> new DriverVehicleController().getAll(ctx));

        app.post("/driver-vehicles", ctx -> new DriverVehicleController().create(ctx));

        app.get("/driver-vehicles/:driver-id/vehicle/:vehicle-id", ctx -> new DriverVehicleController().getOne(ctx,
                ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id")));

        app.patch("/driver-vehicles/:driver-id/vehicle/:vehicle-id", ctx -> new DriverVehicleController().update(ctx,
                ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id")));

        app.delete("/driver-vehicles/:driver-id/vehicle/:vehicle-id", ctx -> new DriverVehicleController().delete(ctx,
                ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id")));
        
    }

    private static void driverVehicleReplacementRoutes(Javalin app){

        app.get("/driver-vehicles-replacements", ctx -> new DriverVehicleReplacementController().getAll(ctx));

        app.post("/driver-vehicles-replacements", ctx -> new DriverVehicleReplacementController().create(ctx));

        app.get("/driver-vehicles/:driver-id/vehicle/:vehicle-id" +
                "/driverv/:driverv-id/vehiclev/:vehiclev-id", ctx -> new DriverVehicleReplacementController().getOne(ctx,
                ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id"),
                ctx.pathParam("driverv-id"), ctx.pathParam("vehiclev-id")));

        app.patch("/driver-vehicles/:driver-id/vehicle/:vehicle-id" +
                "/driverv/:driverv-id/vehiclev/:vehiclev-id", ctx -> new DriverVehicleReplacementController().getOne(ctx,
                ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id"),
                ctx.pathParam("driverv-id"), ctx.pathParam("vehiclev-id")));

        app.delete("/driver-vehicles/:driver-id/vehicle/:vehicle-id" +
                "/driverv/:driverv-id/vehiclev/:vehiclev-id", ctx -> new DriverVehicleReplacementController().getOne(ctx,
                ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id"),
                ctx.pathParam("driverv-id"), ctx.pathParam("vehiclev-id")));
        
    }

    private static void cityRoutes(Javalin app){
        app.get("/cities/state/:state-id", ctx ->
                new CityController().getByState(ctx, ctx.pathParam("state-id")));
    }

    private static void stopRoutes(Javalin app){
        app.get("/stops", ctx ->
                new StopController().getAll(ctx));

        app.get("/stops/:stop-id", ctx ->
                new StopController().getOne(ctx, ctx.pathParam("stop-id")));

        app.get("/stops/initial/:city-id", ctx ->
                new StopController().stopsOfBase(ctx, ctx.pathParam("city-id")));

        app.get("/stops/destinations/:plan-id", ctx ->
                new StopController().stopsOfDestination(ctx, ctx.pathParam("plan-id")));

        app.post("/stops", ctx ->
                new StopController().create(ctx));

        app.patch("/stops/:stop-id", ctx ->
                new StopController().update(ctx, ctx.pathParam("stop-id")));

        app.delete("/stops/:stop-id", ctx ->
                new StopController().delete(ctx, ctx.pathParam("stop-id")));
    }

    private static void passengerRoutes(Javalin app){
        //Passenger

        app.get("/passengers/:passenger-id/plans",
                ctx -> new PassengerController().listPlan(ctx, ctx.pathParam("passenger-id")));

        app.patch("/passengers/:passenger-id/plan/:plan-id/destination/:destination-id",
                ctx -> new PassengerController().enableDestination(ctx, ctx.pathParam("passenger-id"),
                        ctx.pathParam("plan-id"), ctx.pathParam("destination-id")));

        app.delete("/passengers/:passenger-id/plan/:plan-id/destination/:destination-id",
                ctx -> new PassengerController().disableDestination(ctx, ctx.pathParam("passenger-id"),
                        ctx.pathParam("plan-id"), ctx.pathParam("destination-id")));

        app.delete("/passengers/:passenger-id/plan/:plan-id",
                ctx -> new PassengerController().deletePlan(ctx, ctx.pathParam("passenger-id"),
                        ctx.pathParam("plan-id")));
    }

    private static void destinationRoutes(Javalin app){
        //Destination

        app.get("/destinations/city/:city-id", ctx -> new DestinationController().getByCity(ctx,
                ctx.pathParam("city-id")));
    }

    private static void planRoutes(Javalin app){
        //Plan

        app.get("/plans/:plan-id/passengers-count", ctx -> new PlanController()
                .numPassengerOfPlan(ctx, ctx.pathParam("plan-id")));

        app.get("/plans/:plan-id/destinations", ctx -> new PlanController().listDestinations(ctx,
                ctx.pathParam("plan-id")));

        app.post("/plans/:plan-id/destination/:destination-id", ctx -> new PlanController().addDestination(ctx,
                ctx.pathParam("plan-id"), ctx.pathParam("destination-id")));

        app.delete("/plans/:plan-id/destination/:destination-id", ctx -> new PlanController().rmDestination(ctx,
                ctx.pathParam("plan-id"), ctx.pathParam("destination-id")));

        ///
    }

    private static void availabilityRoutes(Javalin app){
        //Availability

        app.get("/availabilities", ctx -> new AvailabilityController().getAll(ctx));

        app.get("/availabilities/:plan-id", ctx -> new AvailabilityController()
                .availabilitiesOfPlanWithDriverAndVehicle(ctx, ctx.pathParam("plan-id")));

        app.post("/availabilities", ctx -> new AvailabilityController().create(ctx));

        app.get("/availabilities/:plan-id/driver/:driver-id/vehicle/" +
                ":vehicle-id/shift/:shift-id/day/:day-id" +
                "/direction/:direction-id/stop/:stop-id", ctx -> new AvailabilityController().getOne(ctx,
                ctx.pathParam("plan-id"), ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id"),
                ctx.pathParam("shift-id"), ctx.pathParam("day-id"), ctx.pathParam("direction-id"),
                ctx.pathParam("stop-id")));

        app.patch("/availabilities/:plan-id/driver/:driver-id/vehicle/" +
                ":vehicle-id/shift/:shift-id/day/:day-id" +
                "/direction/:direction-id/stop/:stop-id", ctx -> new AvailabilityController().update(ctx,
                ctx.pathParam("plan-id"), ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id"),
                ctx.pathParam("shift-id"), ctx.pathParam("day-id"), ctx.pathParam("direction-id"),
                ctx.pathParam("stop-id")));

        app.delete("/availabilities/:plan-id/driver/:driver-id/vehicle/" +
                ":vehicle-id/shift/:shift-id/day/:day-id" +
                "/direction/:direction-id/stop/:stop-id", ctx -> new AvailabilityController().delete(ctx,
                ctx.pathParam("plan-id"), ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id"),
                ctx.pathParam("shift-id"), ctx.pathParam("day-id"), ctx.pathParam("direction-id"),
                ctx.pathParam("stop-id")));

        ///
    }

    private static void reservationRoutes(Javalin app){
        //Reservation

        app.get("/reservations", ctx -> new ReservationController().getAll(ctx));

        app.post("/reservations", ctx -> new ReservationController().create(ctx));

        app.get("/reservations/:plan-id/driver/:driver-id/vehicle/" +
                ":vehicle-id/shift/:shift-id/day/:day-id" +
                "/direction/:direction-id/stop/:stop-id" +
                "/passenger/:passenger-id/", ctx -> new ReservationController().getOne(ctx,
                ctx.pathParam("plan-id"), ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id"),
                ctx.pathParam("shift-id"), ctx.pathParam("day-id"), ctx.pathParam("direction-id"),
                ctx.pathParam("stop-id"), ctx.pathParam("passenger-id")));

        app.get("/reservations/:plan-id" +
                "/passenger/:passenger-id/plan-type/:plan-calc", ctx ->
                new ReservationController().calculatePlanReservationValue(ctx,
                ctx.pathParam("plan-id"),  ctx.pathParam("passenger-id"),
                ctx.pathParam("plan-calc")));

        app.get("/reservations/:plan-id" +
                "/passenger/:passenger-id", ctx ->
                new ReservationController().reservationList(ctx,
                        ctx.pathParam("plan-id"),  ctx.pathParam("passenger-id")));

        app.patch("/reservations/:plan-id/driver/:driver-id/vehicle/" +
                ":vehicle-id/shift/:shift-id/day/:day-id" +
                "/direction/:direction-id/stop/:stop-id" +
                "/passenger/:passenger-id", ctx -> new ReservationController().update(ctx,
                ctx.pathParam("plan-id"), ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id"),
                ctx.pathParam("shift-id"), ctx.pathParam("day-id"), ctx.pathParam("direction-id"),
                ctx.pathParam("stop-id"), ctx.pathParam("passenger-id")));

        app.delete("/reservations/:plan-id/driver/:driver-id/vehicle/" +
                ":vehicle-id/shift/:shift-id/day/:day-id" +
                "/direction/:direction-id/stop/:stop-id" +
                "/passenger/:passenger-id/", ctx -> new ReservationController().delete(ctx,
                ctx.pathParam("plan-id"), ctx.pathParam("driver-id"), ctx.pathParam("vehicle-id"),
                ctx.pathParam("shift-id"), ctx.pathParam("day-id"), ctx.pathParam("direction-id"),
                ctx.pathParam("stop-id"), ctx.pathParam("passenger-id")));
        ///
    }
}
