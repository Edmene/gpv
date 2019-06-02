/*
Copyright 2009-2010 Igor Polevoy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package app.controllers;

import app.controllers.authorization.PasswordHashing;
import app.controllers.authorization.Protected;
import app.enums.UserType;
import app.json.UserJson;
import app.json.UserRegistrationJson;
import app.json.VehicleJson;
import app.models.User;
import app.models.Vehicle;
import app.utils.Db;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import io.javalin.Context;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.NotNull;
import org.postgresql.util.PSQLException;

import java.util.ArrayList;

@Protected
public class UserController extends GenericAppController {

    private ArrayList<UserJson> usersToUserJsonList(LazyList<User> users){
        ArrayList<UserJson> json = new ArrayList<>();
        for (User user : users) {
            json.add(new UserJson(user));
        }
        return json;
    }

    @Override
    public void getAll(@NotNull Context ctx){
        try {
            Base.open(Db.getInstance());
            LazyList<User> results = User.findAll();
            ctx.result(mapper.writeValueAsString(usersToUserJsonList(results)));
            Base.close();
        }
        catch (Exception e){
            ctx.res.setStatus(500);
            e.printStackTrace();
            Base.close();
        }
    }

    @Override
    public void getOne(@NotNull Context ctx, String resourceId){
        try {
            Base.open(Db.getInstance());
            User user = User.findById(Integer.parseInt(resourceId));
            if(user == null){
                ctx.res.setStatus(404);
            }
            else {
                ctx.res.setStatus(200);
                UserJson userJson = new UserJson(user);
                ctx.result(mapper.writeValueAsString(userJson));
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
    public void create(@NotNull Context ctx) {
        try {

            Base.open(Db.getInstance());
            User user = new User();
            UserRegistrationJson userJson = ctx.bodyAsClass(UserRegistrationJson.class);
            if(userRegistration(user, userJson)){
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
            User user = User.findById(Integer.parseInt(resourceId));
            if(user == null){
                ctx.res.setStatus(404);
            }
            else {
                if (user.delete()) {
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

    private boolean userRegistration(User user, UserRegistrationJson userJson){
        try {
            PasswordHashing passwordHashing = new PasswordHashing();

            userJson.password = passwordHashing.hashPassword(userJson.password);
            userJson.setAttributesOfUser(user);
            user.set("extra", passwordHashing.getSalt());
            boolean result = user.saveIt();
            return result;
        }
        catch (Exception e){
            return false;
        }

    }

    @Override
    public void update(@NotNull Context ctx, @NotNull String resourceId){
        try {
            Base.open(Db.getInstance());
            UserRegistrationJson userJson = ctx.bodyAsClass(UserRegistrationJson.class);
            User user = User.findById(Integer.parseInt(resourceId));
            if(user == null){
                ctx.res.setStatus(404);
            }
            else {
                if(userRegistration(user, userJson)){
                    ctx.res.setStatus(200);
                }
                else{
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
}
