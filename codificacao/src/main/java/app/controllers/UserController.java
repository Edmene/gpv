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
import app.models.User;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;

public class UserController extends GenericAppController {

    @Override
    public void index(){

        if("xml".equals(format())){
            render().noLayout().contentType("text/xml");
        }

        if("json".equals(format())){
            render().noLayout().contentType("application/json");
        }

        view("users", User.findAll().toMaps());
    }

    @Override
    public void show(){
        //this is to protect from URL hacking
        User u = User.findById(Integer.parseInt(getId()));
        if(u != null){
            view("user", u);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override @POST
    public void create() throws Exception {
        User user = new User();
        user.fromMap(params1st());

        PasswordHashing passwordHashing = new PasswordHashing();
        user.set("extra", passwordHashing.getSalt());
        user.set("password", passwordHashing.hashPassword(param("password").trim()));
        if(!user.save()){
            flash("message", "Something went wrong, please  fill out all fields");
            flash("errors", user.errors());
            flash("params", params1st());
            redirect(UserController.class, "new_form");
        }else{
            //flash("message", "Novo usuario foi adicionado: " + user.get("name"));
            User user0;
            user0 = (User) User.find("name = ?", param("name")).get(0);
            flash("message", "Novo usuario foi adicionado: " + user.get("password").toString() +
            " " + PasswordHashing.checkPasswordHash(param("password"), user0).salt);
            redirect(UserController.class);
        }
    }

    @Override @DELETE
    public void delete(){

        User u = User.findById(Integer.parseInt(getId()));
        String nome = u.getString("name");
        u.delete();
        flash("message", "User: '" + nome + "' foi deletado");
        redirect(UserController.class);
    }

    @Override @PUT
    public void alterForm(){
        User user = User.findById(Integer.parseInt(getId()));
        if(user != null){
            view("user", user);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @Override @POST
    public void update(){
        User user = new User();
        user.fromMap(params1st());
        user.set("id", Integer.parseInt(param("id")));
        if(!user.save()){
            flash("message", "Something went wrong, please restart the process");
            redirect(UserController.class);
        }
        else{
            flash("message", "Usuario alterado " + user.get("name"));
            redirect(UserController.class);
        }
    }
}
