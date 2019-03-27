package app;

import app.controllers.CityController;
import app.controllers.StateController;
import io.javalin.*;
import io.javalin.apibuilder.ApiBuilder;


public class Main {
    public static void main(String[] args){
        try {

            Javalin app = Javalin.create().enableCorsForOrigin("*").start(7000);
            app.routes(() -> {
                ApiBuilder.crud("states/:state-id", new StateController());
                ApiBuilder.crud("states/:state-id", new CityController());
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}
