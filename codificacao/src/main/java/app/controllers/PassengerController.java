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
import java.util.ArrayList;

public class PassengerController extends GenericAppController {

    public void check(){
        /*
        if(xhr()){
            Map<String, String> map = params1st();
            if(map.keySet().size() > 0) {
                String userName = String.valueOf(map.keySet().toArray()[0]);
                CheckUserJson userJson = new CheckUserJson(User.find("name = ?", userName).size() == 0);
                Gson gson = new Gson();
                respond(gson.toJson(userJson)).contentType("application/json").status(200);
            }
            else {
                CheckUserJson userJson = new CheckUserJson(false);
                Gson gson = new Gson();
                respond(gson.toJson(userJson)).contentType("application/json").status(200);
            }
        }
        */
    }

    private ArrayList<PassengerJson> passengersToPassengerJsonList(LazyList<Passenger> passengers){
        ArrayList<PassengerJson> json = new ArrayList<>();
        for (Passenger passenger : passengers) {
            json.add(new PassengerJson(passenger));
        }
        return json;
    }

    @Override
    public void getAll(@NotNull Context ctx){
        /*
        if(!negateAccess(UserType.P)) {
            
        }
        */
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
    public void create(@NotNull Context ctx){
        try {
            PassengerRegistration passenger  = ctx.bodyAsClass(PassengerRegistration.class);
            DocumentValidation validation = new DocumentValidation();
            if(validation.validateCpf(passenger.cpf)) {

                DB db = Base.open(Db.getInstance());
                PasswordHashing hashing = new PasswordHashing();


                CallableStatement passengerCreationFunction = db.connection().prepareCall(
                        "SELECT passenger_creation(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                passengerCreationFunction.setString(1, passenger.userName);
                passengerCreationFunction.setString(2, hashing.hashPassword(passenger.password));
                passengerCreationFunction.setString(3, hashing.getSalt());
                passengerCreationFunction.setString(4, passenger.name);
                passengerCreationFunction.setString(5, passenger.surname);
                passengerCreationFunction.setString(6, passenger.cpf);
                passengerCreationFunction.setString(7, passenger.rg);
                passengerCreationFunction.setString(8, passenger.telephone);
                passengerCreationFunction.setString(9, passenger.email);
                passengerCreationFunction.setDate(10, Date.valueOf(passenger.birthDate));
                if (passengerCreationFunction.execute()) {
                    ctx.res.setStatus(200);
                } else {
                    ctx.res.setStatus(400);
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
        /*
        if(User.find("name = ?", param("user_name")).size() == 0) {
            User user = new User();
            user.fromMap(params1st());
            PasswordHashing passwordHashing = new PasswordHashing();
            user.set("extra", passwordHashing.getSalt());
            user.set("password", passwordHashing.hashPassword(param("password").trim()));
            user.set("name", param("user_name"));
            if (!user.save()) {




            } else {
                LazyList<Model> u = User.find("name = ?", param("user_name"));
                Passenger passenger = new Passenger();
                passenger.fromMap(params1st());
                passenger.set("user_id", u.get(0).getId());

                passenger.set("telephone", TransformMaskeredInput.format(param("telephone")));
                passenger.set("cpf", TransformMaskeredInput.format(param("cpf")));
                passenger.set("rg", TransformMaskeredInput.format(param("rg")));

                LocalDate date = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(param("birth_date")));
                passenger.setDate("birth_date", date);
                //u.get(0).add(passenger);

                if (!passenger.insert()) {
                    user.setId(u.get(0).getId()).delete();




                } else {

                    //

                }
            }
        }
        else {


        }
        */
    }

    @Override
    public void delete(@NotNull Context ctx, @NotNull String resourceId){
        /*
        if(!negateAccess(UserType.P, Integer.parseInt(getId())) || !negateAccess(UserType.A)) {
            Passenger passenger = Passenger.findById(Integer.parseInt(getId()));
            String name = passenger.getString("name");
            passenger.delete();
            User.findById(Integer.parseInt(getId())).delete();
            if (session("user").toString().contentEquals(((name)))) {
                
            } else {
                
                
            }
        }
        */
        try{
            Base.open(Db.getInstance());
            Passenger passenger = Passenger.findById(Integer.parseInt(resourceId));
            User user = User.findById(passenger.getInteger("user_id"));
            if(user.delete()){
                if(passenger.delete()){
                    ctx.res.setStatus(200);
                }
                else {
                    ctx.res.setStatus(400);
                }
            }
            else{
                ctx.res.setStatus(400);
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
        /*
        if(!negateAccess(UserType.P, Integer.parseInt(getId())) || !negateAccess(UserType.A)) {
            User user = User.findById(Integer.parseInt(param("id")));
            String oldName = user.getString("name");
            user.fromMap(params1st());
            PasswordHashing passwordHashing = new PasswordHashing();
            user.set("extra", passwordHashing.getSalt());
            user.set("password", passwordHashing.hashPassword(param("password").trim()));
            user.set("name", param("user_name"));
            user.set("id", Integer.parseInt(param("id")));
            if (!user.save()) {
                
                
            } else {
                Passenger passenger = new Passenger();
                passenger.fromMap(params1st());
                passenger.set("user_id", Integer.parseInt(param("id")));

                passenger.set("telephone", TransformMaskeredInput.format(param("telephone")));
                passenger.set("cpf", TransformMaskeredInput.format(param("cpf")));
                passenger.set("rg", TransformMaskeredInput.format(param("rg")));

                LocalDate date = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(param("birth_date")));
                passenger.setDate("birth_date", date);
                if (!passenger.save()) {
                    
                    
                } else {
                    
                    
                    if (session("user").toString().contentEquals(((oldName)))) {
                        session("user", (String) passenger.get("name"));
                    }
                }
            }
        }
        */
    }

    public void listPlan(){
        /*
        if(!negateAccess(UserType.P, Integer.parseInt(getId())) || !negateAccess(UserType.A)) {
            
                    PassengerDestinationWithInfo.find("passenger_id = ?",
                            Integer.parseInt(getId())).toMaps());
        }
        */
    }

    public void changePlan(){
        /*
        if(!negateAccess(UserType.P, Integer.parseInt(param("passenger_id"))) || !negateAccess(UserType.A)) {
            PassengerPlans passengerPlans = (PassengerPlans) PassengerPlans.find("plan_id = ? AND" +
                            " destination_id = ? AND passenger_id = ?", Integer.parseInt(param("plan_id")),
                    Integer.parseInt(param("destination_id")), Integer.parseInt(param("passenger_id"))).get(0);

            if (passengerPlans.getBoolean("status")) {
                passengerPlans.set("status", false);
                passengerPlans.save();

                if (PassengerPlans.find("plan_id = ? AND" +
                                " passenger_id = ? AND status IS TRUE", Integer.parseInt(param("plan_id")),
                        Integer.parseInt(param("passenger_id"))).size() == 0) {
                    LazyList<Reservation> reservations = Reservation.find("plan_id = ? AND passenger_id = ?" +
                                    " AND date IS NULL",
                            Integer.parseInt(param("plan_id")),
                            Integer.parseInt(param("passenger_id")));
                    for (Reservation reservation : reservations) {
                        reservation.set("alteration_date", LocalDate.now().plusDays(15));
                        reservation.save();
                    }
                }
                
            } else {
                passengerPlans.set("status", true);
                passengerPlans.save();
                
            }
            
        }
        */
    }
}
