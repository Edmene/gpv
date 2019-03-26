package app.controllers;

import app.controllers.authorization.PasswordHashing;
import app.models.Passenger;
import app.models.User;
import app.utils.TransformMaskeredInput;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;
import org.javalite.activeweb.annotations.POST;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PassengerCreationController extends GenericAppController {

    @Override @POST
    public void create() throws Exception{
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
    }
}
