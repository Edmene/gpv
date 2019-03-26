package app.controllers;

import app.controllers.authorization.PasswordHashing;
import app.models.User;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;
import org.javalite.activeweb.annotations.POST;

public class LoginController extends GenericAppController {

    @Override
    public void index(){
        if(session().containsKey("accessLevel")){
            
            
        }
    }

    @POST
    public void login() throws Exception {

        if(session().containsKey("accessLevel")){
            
            
        }
        else {
            if (blank("user", "password")) {
                
                
            }
            else {

                LazyList<Model> users = User.find("name = ?", param("user"));
                Boolean isInDatabase = false;

                if (users.size() > 0) {
                    User user = (User) users.get(0);
                    if (PasswordHashing.checkPasswordHash(param("password").trim(), user)) {
                        session("id", user.getInteger("id"));
                        session("user", param("user"));
                        session().put("accessLevel", user.get("type"));
                        isInDatabase = true;
                        
                    }
                }
                if (!isInDatabase) {
                    
                    
                }

            }
        }
    }

    public void logout(){
        session("id", null);
        session("user", null);
        session("accessLevel", null);
        
    }
}
