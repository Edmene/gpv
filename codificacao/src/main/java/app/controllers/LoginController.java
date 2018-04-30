package app.controllers;

import app.controllers.authorization.PasswordHashing;
import app.models.User;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;
import org.javalite.activeweb.annotations.POST;

public class LoginController extends GenericAppController {

    @Override
    public void index(){
        if(session().containsKey("acessLevel")){
            flash("message", "Voce ja esta logado");
            redirect(HomeController.class);
        }
    }

    @POST
    public void login() throws Exception {

        if(session().containsKey("acessLevel")){
            flash("message", "Voce ja esta logado");
            redirect(HomeController.class);
        }
        else {
            if (blank("user", "password")) {
                flash("message", "Informe a senha e usuario");
                redirect();
            }
            else {
                Base.open("org.postgresql.Driver", "jdbc:postgresql://172.17.0.2:5432/gpv", "postgres", "postgres");
                LazyList<Model> users = User.find("name = ?", param("user"));
                Boolean isInDatabase = false;

                if (users.size() > 0) {
                    User user = (User) users.get(0);
                    if (PasswordHashing.checkPasswordHash(param("password").trim(), user)) {
                        session("id", user.getInteger("id"));
                        session("user", param("user"));
                        session().put("acessLevel", user.get("type"));
                        isInDatabase = true;
                        redirect(HomeController.class);
                    }
                }
                if (!isInDatabase) {
                    flash("message", "Usuario ou senha incorretos");
                    redirect();
                }

                Base.close();
            }
        }
    }

    public void logout(){
        session("id", null);
        session("user", null);
        session("acessLevel", null);
        redirect(HomeController.class);
    }
}
