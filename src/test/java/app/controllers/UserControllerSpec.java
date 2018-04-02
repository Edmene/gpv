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
import org.javalite.activeweb.DBControllerSpec;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author Igor Polevoy
 */
public class UserControllerSpec extends DBControllerSpec {

    String nome = "160413402X";
    String senha = "Erich Maria Remarque";
    Object id;

    @Before
    public void before() {
        User.deleteAll();
        User u = (User) User.createIt("nome", nome, "senha", senha);
        id = u.getId();
        User.createIt("nome", "rwerewr", "senha", "a1b2c3d4");

    }

    @Test
    public void shouldListAllBooks() {
        request().get("index"); //<< this is where we execute the controller
        List usuarios = (List) assigns().get("usuarios");
        a(usuarios.size()).shouldBeEqual(2);
    }

    @Test
    public void shouldFindOneBookByIsbn() {
        request().param("id", id).get("show"); //<< this is where we execute the controller and pass a parameter
        User user = (User) assigns().get("user");
        a(user.get("senha")).shouldBeEqual(senha);
    }

    @Test
    public void shouldCreateNewBook() {
        //create a fourth book
        request().param("nome", "12345").param("senha", "Author 1").post("create");
        //get list of books
        request().get("index");
        List usuarios = (List) assigns().get("usuarios");
        a(usuarios.size()).shouldBeEqual(3);
    }

    @Test
    public void shouldDeleteBookById() {
        User b = (User) User.findAll().get(0);

        request().param("id",  b.getId()).delete("delete");
        
        a(redirected()).shouldBeTrue();
        a(User.count()).shouldBeEqual(1);
        a(flash("message")).shouldNotBeNull();
    }

    @Test
    public void shouldShowBookByIdAndVerifyGeneratedHTML() {
        User u = (User) User.findAll().get(0);

        request().integrateViews().param("id",  u.getId()).get("show");

        User user = (User) assigns().get("book");
        a(user.get("nome")).shouldBeEqual(u.get("nome"));
        String html = responseContent();
        a(html.contains(u.getString("nome"))).shouldBeTrue();
    }
}
