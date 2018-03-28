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

package app.models;

import org.javalite.activeweb.DBSpec;
import org.junit.Test;

import java.util.List;

/**
 * @author Igor Polevoy
 */
public class UsuarioSpec extends DBSpec {

    @Test
    public void shouldValidateRequiredAttributes(){
        Usuario usuario = new Usuario();
        a(usuario).shouldNotBe("valid");

        usuario.set("nome", "fake title", "senha", "fake author");
        a(usuario).shouldBe("valid");
    }

}

