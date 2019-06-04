package app.controllers;

import app.json.CityJson;
import app.models.City;
import app.utils.Db;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CityController extends GenericAppController {

    private ArrayList<CityJson> citiesToCityJsonList(LazyList<City> cities){
        ArrayList<CityJson> json = new ArrayList<>();
        for (City city : cities) {
            json.add(new CityJson(city));
        }
        return json;
    }

    @Override
    public void getAll(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            LazyList<City> results = City.findAll();
            ctx.result(mapper.writeValueAsString(citiesToCityJsonList(results)));
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
            City city = City.findById(Integer.parseInt(resourceId));
            if(city == null){
                ctx.res.setStatus(404);
            }
            else {
                ctx.res.setStatus(200);
                CityJson cityJson = new CityJson(city);
                ctx.result(mapper.writeValueAsString(cityJson));
            }
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    public void getByState(@NotNull Context ctx, @NotNull String resourceId){
        try{
            Base.open(Db.getInstance());
            LazyList<City> cities = City.find("state_id = ?", Integer.parseInt(resourceId));
            ctx.result(mapper.writeValueAsString(citiesToCityJsonList(cities)));
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
            Base.open(Db.getInstance());
            City city = new City();
            CityJson cityJson  = ctx.bodyAsClass(CityJson.class);
            cityJson.setAttributesOfCity(city);
            if(city.saveIt()){
                ctx.res.setStatus(200);
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

    @Override
    public void update(@NotNull Context ctx, @NotNull String resourceId){
        try {
            Base.open(Db.getInstance());
            City city = City.findById(Integer.parseInt(resourceId));
            CityJson cityJson = ctx.bodyAsClass(CityJson.class);
            if(city == null) {
                ctx.res.setStatus(404);
                return;
            }
            if(city.getId() != cityJson.key){
                ctx.res.setStatus(400);
                return;
            }
            cityJson.setAttributesOfCity(city);
            if(city.save()){
                ctx.res.setStatus(200);
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

    @Override
    public void delete(@NotNull Context ctx, @NotNull String resourceId){
        try{
            Base.open(Db.getInstance());
            City city = City.findById(Integer.parseInt(resourceId));
            if(city == null){
                ctx.res.setStatus(404);
            }
            else {
                if (city.delete()) {
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

    /*
    Adicionar triggers para os processos de criacao, alteracao e delecao
     */
}
