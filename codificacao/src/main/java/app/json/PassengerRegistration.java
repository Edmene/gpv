package app.json;

import app.utils.JavaScriptDateDeserializer;
import app.utils.JavaScriptDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

public class PassengerRegistration {
    public String userName, password, name, surname, cpf, rg, telephone, email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    @JsonSerialize(using = JavaScriptDateSerializer.class)
    @JsonDeserialize(using = JavaScriptDateDeserializer.class)
    public LocalDate birthDate;

    public PassengerRegistration(){}

    public PassengerRegistration(String userName, String password,
                                 String name, String surname, String cpf, String rg,
                                 String telephone, String email, LocalDate birthDate){
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.cpf = cpf;
        this.rg = rg;
        this.telephone = telephone;
        this.email = email;
        this.birthDate = birthDate;
    }
}
