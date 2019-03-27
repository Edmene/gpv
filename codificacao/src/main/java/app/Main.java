package app;

import app.models.State;
import io.javalin.*;

import org.postgresql.Driver;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args){
        Javalin app = Javalin.create().start(7000);

        try {
            Base.open();
            System.out.println(State.findAll());
            Base.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        /*
        Driver test = new Driver();
        Properties properties = new Properties();
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "postgres");
        try {
            Connection connection  = test.connect("jdbc:postgresql://172.17.0.2:5432/gpv", properties);
            System.out.println(connection.getMetaData());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
        app.get("/", ctx -> ctx.result("FOI"));


    }
}
