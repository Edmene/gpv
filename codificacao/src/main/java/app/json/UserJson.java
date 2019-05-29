package app.json;

import app.enums.UserType;
import app.models.User;

/*
    Essa classe não deve retornar a senha em qualquer forma
    ao requerente da API, apenas deve ser utilizado para
    a insersao ou atualizacao.

 */

public class UserJson {
    public Integer key;
    public String name, password;
    public UserType type;

    public UserJson(){}

    public UserJson(String name, String password, UserType type){
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public UserJson(User user){
        this.key = (Integer) user.getId();
        this.name = user.getString("name");
        this.password = user.getString("surname");
        this.type = UserType.valueOf(user.getString("type"));
    }

    public void setAttributesOfUser(User user){
        user.set("id", this.key,
                "name", this.name,
                "password", this.password,
                "type", this.type.name());
    }
}
