package app.controllers;

import app.json.DriverJson;
import app.models.Driver;
import app.utils.Db;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;
import app.utils.DocumentValidation;

import java.util.ArrayList;

public class DriverController extends GenericAppController {

    private ArrayList<DriverJson> driversToDriverJsonList(LazyList<Driver> drivers){
        ArrayList<DriverJson> json = new ArrayList<>();
        for (Driver driver : drivers) {
            json.add(new DriverJson(driver));
        }
        return json;
    }

    @Override
    public void getAll(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            LazyList<Driver> results = Driver.findAll();
            ctx.result(mapper.writeValueAsString(driversToDriverJsonList(results)));
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    @Override
    public void getOne(@NotNull Context ctx, @NotNull String resourceId){
        try{
            Base.open(Db.getInstance());
            Driver driver = Driver.findById(Integer.parseInt(resourceId));
            if(driver == null){
                ctx.res.setStatus(404);
            }
            else {
                ctx.res.setStatus(200);
                DriverJson driverJson = new DriverJson(driver);
                ctx.result(mapper.writeValueAsString(driverJson));
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    @Override
    public void create(@NotNull Context ctx){
        try {
            DocumentValidation validation = new DocumentValidation();
            Base.open(Db.getInstance());
            Driver driver = new Driver();
            DriverJson driverJson  = ctx.bodyAsClass(DriverJson.class);
            driverJson.setAttributesOfDriver(driver);
            if(validation.validateCpf(driverJson.cpf) && validation.validateChn(driverJson.cnh)) {
                if (driver.saveIt()) {
                    ctx.res.setStatus(200);
                } else {
                    ctx.res.setStatus(400);
                }
            }
            else {
                System.out.println(validation.validateCpf(driverJson.cpf));
                System.out.println(validation.validateCpf(driverJson.cnh));
                ctx.res.setStatus(400);
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    @Override
    public void delete(@NotNull Context ctx, @NotNull String resourceId){
        try {
            Base.open(Db.getInstance());
            Driver driver = Driver.findById(Integer.parseInt(resourceId));
            if(driver == null){
                ctx.res.setStatus(404);
            }
            else {
                if (driver.delete()) {
                    ctx.res.setStatus(200);
                } else {
                    ctx.res.setStatus(400);
                }
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    @Override
    public void update(@NotNull Context ctx, @NotNull String resourceId){
        try {
            DocumentValidation validation = new DocumentValidation();
            Base.open(Db.getInstance());
            Driver driver = Driver.findById(Integer.parseInt(resourceId));
            DriverJson driverJson = ctx.bodyAsClass(DriverJson.class);
            if(driver == null) {
                ctx.res.setStatus(404);
                return;
            }
            if(driver.getId() != driverJson.key){
                ctx.res.setStatus(400);
                return;
            }
            driverJson.setAttributesOfDriver(driver);
            if(validation.validateCpf(driverJson.cpf) && validation.validateChn(driverJson.cnh)) {
                if (driver.save()) {
                    ctx.res.setStatus(200);
                } else {
                    ctx.res.setStatus(400);
                }
            }
            else{
                ctx.res.setStatus(400);
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    /*
    Adicionar triggers para os processos de criacao, alteracao e delecao
     */
}
