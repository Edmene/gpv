package app.controllers;


import app.enums.UserType;
import app.json.CheckUserJson;
import app.models.*;
import app.utils.TransformMaskeredInput;
import org.javalite.activejdbc.LazyList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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

    @Override
    public void index(){
        /*
        if(!negateAccess(UserType.P)) {
            
        }
        */
    }

    @Override
    public void show(){
        /*
        if(!negateAccess(UserType.P, Integer.parseInt(getId())) || !negateAccess(UserType.A)) {
            //this is to protect from URL hacking
            Passenger passenger = Passenger.findById(Integer.parseInt(getId()));
            if (passenger != null) {
                
            } else {
                
                
            }
        }
        */
    }

    @Override
    public void delete(){
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

    }

    @Override
    public void alterForm(){
        /*
        if(!negateAccess(UserType.P, Integer.parseInt(getId())) || !negateAccess(UserType.A)) {
            Passenger passenger = Passenger.findById(Integer.parseInt(getId()));
            User user = User.findById(Integer.parseInt(getId()));
            LocalDate date = LocalDate.parse(passenger.get("birth_date").toString());
            if (passenger != null) {
                
            } else {
                
                
            }
        }
        */
    }

    @Override
    public void update() throws Exception{
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
