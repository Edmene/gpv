package app.controllers;


import app.controllers.authorization.PasswordHashing;
import app.json.PassengerJson;
import app.json.PassengerRegistration;
import app.models.*;
import app.utils.Db;
import app.utils.DocumentValidation;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.DB;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;

public class PassengerController extends GenericAppController {

    private ArrayList<PassengerJson> passengersToPassengerJsonList(LazyList<Passenger> passengers){
        ArrayList<PassengerJson> json = new ArrayList<>();
        for (Passenger passenger : passengers) {
            json.add(new PassengerJson(passenger));
        }
        return json;
    }

    @Override
    public void getAll(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            LazyList<Passenger> results = Passenger.findAll();
            ctx.result(mapper.writeValueAsString(passengersToPassengerJsonList(results)));
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    @Override
    public void getOne(@NotNull Context ctx, @NotNull String resourceId){
        try {
            Base.open(Db.getInstance());
            Passenger passenger = Passenger.findById(Integer.parseInt(resourceId));
            if(passenger == null){
                ctx.res.setStatus(404);
                ctx.result("Passenger not found");
            }
            else {
                PassengerJson passengerJson = new PassengerJson(passenger);
                ctx.result(mapper.writeValueAsString(passengerJson));
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    @Override
    public void create(@NotNull Context ctx){
        try {
            PassengerRegistration passenger  = ctx.bodyAsClass(PassengerRegistration.class);
            DocumentValidation validation = new DocumentValidation();
            if(validation.validateCpf(passenger.cpf)) {

                DB db = Base.open(Db.getInstance());
                PasswordHashing hashing = new PasswordHashing();


                CallableStatement passengerCreationFunction = db.connection().prepareCall(
                        "{? = call passenger_creation(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

                passengerCreationFunction.registerOutParameter(1, Types.BOOLEAN);

                passengerCreationFunction.setString(2, passenger.userName);
                passengerCreationFunction.setString(3, hashing.hashPassword(passenger.password));
                passengerCreationFunction.setString(4, hashing.getSalt());
                passengerCreationFunction.setString(5, passenger.name);
                passengerCreationFunction.setString(6, passenger.surname);
                passengerCreationFunction.setString(7, passenger.cpf);
                passengerCreationFunction.setString(8, passenger.rg);
                passengerCreationFunction.setString(9, passenger.telephone);
                passengerCreationFunction.setString(10, passenger.email);
                passengerCreationFunction.setDate(11, Date.valueOf(passenger.birthDate));
                passengerCreationFunction.execute();

                boolean response = passengerCreationFunction.getBoolean(1);
                if (response) {
                    ctx.res.setStatus(200);
                } else {
                    ctx.res.setStatus(400);
                    ctx.result("Invalid data received");
                }
                Base.close();
            }
            else{
                ctx.res.setStatus(400);
                ctx.result("CPF is invalid");
            }
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            ctx.result(e.getMessage());
            e.printStackTrace();
            Base.close();
        }

    }

    @Override
    public void delete(@NotNull Context ctx, @NotNull String resourceId){
        try{
            Base.open(Db.getInstance());
            Passenger passenger = Passenger.findById(Integer.parseInt(resourceId));
            if(passenger == null){
                ctx.res.setStatus(404);
            }
            else {
                if (passenger.delete()) {
                    ctx.res.setStatus(200);
                } else {
                    ctx.res.setStatus(400);
                }
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    @Override
    public void update(@NotNull Context ctx, @NotNull String resourceId){
        DocumentValidation validation = new DocumentValidation();
        try {
            Base.open(Db.getInstance());
            PassengerJson passenger  = ctx.bodyAsClass(PassengerJson.class);
            if(validation.validateCpf(passenger.cpf)) {
                Passenger p = Passenger.findById(Integer.parseInt(resourceId));
                if(p == null) {
                    ctx.res.setStatus(404);
                    return;
                }
                if(p.getId() != passenger.userKey){
                    ctx.res.setStatus(400);
                    return;
                }
                passenger.setAttributesOfPassenger(p);
                p.setInteger("user_id", Integer.parseInt(resourceId));
                if (p.saveIt()) {
                    ctx.res.setStatus(200);
                }
                else {
                    ctx.res.setStatus(400);
                    ctx.result("Invalid data received");
                }
                Base.close();
            }
            else{
                ctx.res.setStatus(400);
                ctx.result("CPF is invalid");
                Base.close();
            }
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            ctx.result(e.getMessage());
            e.printStackTrace();
            Base.close();
        }
    }

    public void listPlan(Context ctx, String resourceId){
        try {
            Base.open(Db.getInstance());
            ctx.result(PassengerDestinationWithInfo.find("passenger_id = ?",
                    Integer.parseInt(resourceId)).toJson(false));
            Base.close();

        }
        catch (Exception e){
            ctx.res.setStatus(500);
            ctx.result(e.getMessage());
            e.printStackTrace();
            Base.close();
        }
    }

    public void disableDestination(Context ctx, String resourceId, String planId, String destinationId){
        changeDestination(ctx, resourceId, planId, destinationId, false);
    }

    private void changeDestination(Context ctx, String resourceId, String planId, String destinationId, boolean status){
        try {
            Base.open(Db.getInstance());
            PassengerPlans pp = PassengerPlans.findByCompositeKeys(Integer.parseInt(resourceId),
                    Integer.parseInt(destinationId), Integer.parseInt(planId));
            if(pp != null) {
                pp.setBoolean("status", status);
                if (pp.saveIt()) {
                    ctx.res.setStatus(200);
                } else {
                    ctx.res.setStatus(400);
                    ctx.result("Invalid data received");
                }
            }
            else{
                ctx.res.setStatus(404);
            }
            Base.close();

        }
        catch (Exception e){
            ctx.res.setStatus(500);
            ctx.result(e.getMessage());
            e.printStackTrace();
            Base.close();
        }
    }

    public void enableDestination(Context ctx, String resourceId, String planId, String destinationId){
        changeDestination(ctx, resourceId, planId, destinationId, true);
    }

    public void deletePlan(Context ctx, String resourceId, String planId){
        try{
            DB db = Base.open(Db.getInstance());

            CallableStatement passengerCreationFunction = db.connection().prepareCall(
                    "{? = call passenger_plan_unsubscribe(?, ?)}");

            passengerCreationFunction.registerOutParameter(1, Types.BOOLEAN);

            passengerCreationFunction.setInt(2, Integer.parseInt(resourceId));
            passengerCreationFunction.setInt(3, Integer.parseInt(planId));
            passengerCreationFunction.execute();

            boolean response = passengerCreationFunction.getBoolean(1);
            if (response) {
                ctx.res.setStatus(200);
            } else {
                ctx.res.setStatus(404);
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            ctx.result(e.getMessage());
            e.printStackTrace();
            Base.close();
        }
    }
}
