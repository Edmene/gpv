package app.controllers;

import app.enums.UserType;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

public abstract class GenericAppController extends AppController {

    public void index(){}

    @DELETE
    public void delete() {}

    @POST
    public void update() throws Exception{}

    @POST
    public void create() throws Exception{}

    @PUT
    public void alterForm(){}

    public void newForm(){}

    public void show(){}

    boolean negateAccess(UserType type){
        if(session("accessLevel").toString().contains(type.name())) {
            flash("message", "Operação ilegal para o usuário");
            redirect(HomeController.class);
            return true;
        }
        else {
            return false;
        }
    }

    boolean negateAccess(UserType type, Integer userId){
        if(session("accessLevel").toString().contains(type.name()) && userId != session("id")) {
            flash("message", "Operação ilegal para o usuário");
            redirect(HomeController.class);
            return true;
        }
        else {
            return false;
        }
    }
}
