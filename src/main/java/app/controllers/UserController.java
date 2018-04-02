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

import app.models.User;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;

/**
 * @author Igor Polevoy
 */
public class UserController extends AppController {

    public void index(){

        if("xml".equals(format())){
            render().noLayout().contentType("text/xml");
        }

        if("json".equals(format())){
            render().noLayout().contentType("application/json");
        }

        view("users", User.findAll().toMaps());
    }

    public void show(){
        //this is to protect from URL hacking
        User u = (User) User.findById(getId());
        if(u != null){
            view("user", u);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @POST
    public void create(){
        User user = new User();
        user.fromMap(params1st());
        if(!user.save()){
            flash("message", "Something went wrong, please  fill out all fields");
            flash("errors", user.errors());
            flash("params", params1st());
            redirect(UserController.class, "new_form");
        }else{
            flash("message", "New book was added: " + user.get("name"));
            redirect(UserController.class);
        }
    }

    @DELETE
    public void delete(){

        User u = User.findById(Integer.parseInt(getId()));
        String nome = u.getString("nome");
        u.delete();
        flash("message", "User: '" + nome + "' was deleted");
        redirect(UserController.class);
    }

    public void newForm(){}
}
