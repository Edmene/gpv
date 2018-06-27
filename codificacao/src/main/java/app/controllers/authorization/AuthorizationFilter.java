package app.controllers.authorization;

import app.controllers.HomeController;
import app.controllers.LoginController;
import app.enums.UserType;
import org.javalite.activeweb.controller_filters.HttpSupportFilter;

/**
 * @author Igor Polevoy on 9/29/14.
 */
public class AuthorizationFilter extends HttpSupportFilter {
    @Override
    public void before() {

        if(!controllerProtected() && !controllerProtectedAdministrative()){
            return;// allow to fall to controller
        }
        else {
            if(session().get("user") == null){
                redirect(LoginController.class);
            }
            else {
                if(controllerProtectedAdministrative() &&
                        !session().get("accessLevel").toString().contains(UserType.A.name())){
                    redirect(HomeController.class);
                }
            }
        }
    }

    private boolean controllerProtected() {
        return getRoute().getController().getClass().getAnnotation(Protected.class) != null;
    }

    private boolean controllerProtectedAdministrative() {
        return getRoute().getController().getClass().getAnnotation(ProtectedAdministrative.class) != null;
    }
}