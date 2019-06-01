package app;

import app.utils.RoutesSetUp;
import io.javalin.Javalin;


public class Main {
    public static void main(String[] args){
        /*
            Falta availability, reservation

         */

        try {
            Javalin app = Javalin.create().enableCorsForOrigin("*").start(7000);
            RoutesSetUp.defineRoutes(app);

        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}
