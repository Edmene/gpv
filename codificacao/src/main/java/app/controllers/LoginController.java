package app.controllers;


public class LoginController extends GenericAppController {

    @Override
    public void index(){
        /*
        if(session().containsKey("accessLevel")){
            
            
        }
        */
    }

    public void login() throws Exception {
        /*

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
        */
    }

    public void logout(){
        /*
        session("id", null);
        session("user", null);
        session("accessLevel", null);
        */
        
    }
}
