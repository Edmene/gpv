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
import app.models.User;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;

@Protected
public class UserController extends GenericAppController {

    @Override
    public void getAll(@NotNull Context ctx){
        /*
        if(!negateAccess(UserType.P)) {
            
        }
        */
    }

    @Override
    public void create(@NotNull Context ctx) {
        /*
        if(!negateAccess(UserType.P)) {
            User user = new User();
            user.fromMap(params1st());

            PasswordHashing passwordHashing = new PasswordHashing();
            user.set("extra", passwordHashing.getSalt());
            user.set("password", passwordHashing.hashPassword(param("password").trim()));
            if(!user.save()){
                
                
                
                
            }else{
                
                
            }
        }
        */
    }

    @Override
    public void delete(@NotNull Context ctx, @NotNull String contentId){
        /*
        if(!negateAccess(UserType.P)) {
            User u = User.findById(Integer.parseInt(getId()));
            String name = u.getString("name");
            u.delete();
            if (!session().isEmpty()) {
                if (session("user").toString().contentEquals(name)) {
                    
                } else {
                    
                    
                }
            } else {
                
                
            }
        }
        */
    }

    @Override
    public void update(@NotNull Context ctx, @NotNull String contentId){
        /*
        if(!negateAccess(UserType.P)) {
            User user = User.findById(Integer.parseInt(param("id")));
            String oldName = user.getString("name");

            user.fromMap(params1st());
            PasswordHashing passwordHashing = new PasswordHashing();
            user.set("extra", passwordHashing.getSalt());
            user.set("password", passwordHashing.hashPassword(param("password").trim()));
            user.set("id", Integer.parseInt(param("id")));

            if (!user.save()) {
                
                
            } else {
                
                if (session("user").toString().contentEquals(((oldName)))) {
                    session("user", (String) user.get("name"));
                }
                

            }
        }
        */
    }
}
