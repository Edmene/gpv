package app.json;

import app.enums.UserType;
import app.models.User;

/*
    Essa classe não deve retornar a senha em qualquer forma
    ao requerente da API, apenas deve ser utilizado para
    a insersao ou atualizacao.

 */

public class UserRegistrationJson {
    public String name, password;
    public UserType type;

    public UserRegistrationJson(){
        this.type = UserType.A;
    }

    public UserRegistrationJson(String name, String password){
        this.name = name;
        this.password = password;
        this.type = UserType.A;
    }

    public UserRegistrationJson(User user){
        this.name = user.getString("name");
        this.password = user.getString("password");
        this.type = UserType.valueOf(user.getString("type"));
    }

    public void setAttributesOfUser(User user){
        user.set("name", this.name,
                "password", this.password,
                "type", this.type.name());
    }
}
