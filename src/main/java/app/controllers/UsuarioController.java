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

import app.models.Usuario;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;

/**
 * @author Igor Polevoy
 */
public class UsuarioController extends AppController {

    public void index(){

        if("xml".equals(format())){
            render().noLayout().contentType("text/xml");
        }

        if("json".equals(format())){
            render().noLayout().contentType("application/json");
        }

        view("usuarios", Usuario.findAll().toMaps());
    }

    public void show(){
        //this is to protect from URL hacking
        Usuario u = (Usuario) Usuario.findById(getId());
        if(u != null){
            view("usuario", u);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @POST
    public void create(){
        Usuario usuario = new Usuario();
        usuario.fromMap(params1st());
        if(!usuario.save()){
            flash("message", "Something went wrong, please  fill out all fields");
            flash("errors", usuario.errors());
            flash("params", params1st());
            redirect(UsuarioController.class, "new_form");
        }else{
            flash("message", "New book was added: " + usuario.get("nome"));
            redirect(UsuarioController.class);
        }
    }

    @DELETE
    public void delete(){

        Usuario u = (Usuario) Usuario.findById(getId());
        String nome = u.getString("nome");
        u.delete();
        flash("message", "Usuario: '" + nome + "' was deleted");
        redirect(UsuarioController.class);
    }

    public void newForm(){}
}
