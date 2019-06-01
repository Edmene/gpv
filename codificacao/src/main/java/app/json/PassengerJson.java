package app.json;

import app.models.Passenger;
import app.utils.JavaScriptDateDeserializer;
import app.utils.JavaScriptDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Date;
import java.time.LocalDate;

public class PassengerJson {
    public Integer key, userKey;
    public String name, surname, cpf, rg, telephone, email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    @JsonSerialize(using = JavaScriptDateSerializer.class)
    @JsonDeserialize(using = JavaScriptDateDeserializer.class)
    public LocalDate birthDate;

    public PassengerJson(){}

    public PassengerJson(String name, String surname, String cpf, String rg,
                         String telephone, String email, Integer userKey,
                         LocalDate birthDate){
        this.name = name;
        this.surname = surname;
        this.cpf = cpf;
        this.rg = rg;
        this.telephone = telephone;
        this.email = email;
        this.userKey = userKey;
        this.birthDate = birthDate;
    }

    public PassengerJson(Passenger passenger){
        this.key = (Integer) passenger.getId();
        this.name = passenger.getString("name");
        this.surname = passenger.getString("surname");
        this.cpf = passenger.getString("cpf");
        this.rg = passenger.getString("rg");
        this.userKey = passenger.getInteger("user_id");
        this.telephone = passenger.getString("telephone");
        this.email = passenger.getString("email");
        this.birthDate = passenger.getDate("birth_date").toLocalDate();
    }

    public void setAttributesOfPassenger(Passenger passenger){
        passenger.set("id", this.key,
                "name", this.name,
                "surname", this.surname,
                "cpf", this.cpf,
                "rg", this.rg,
                "user_id", this.userKey,
                "telephone", this.telephone,
                "email", this.email,
                "birth_date", Date.valueOf(this.birthDate));
    }

}
