package app;

import io.javalin.*;

import org.javalite.activejdbc.Base;

public class Main {
    public static void main(String[] args){
        Base.open();
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> ctx.result("Hello World"));
    }
}
