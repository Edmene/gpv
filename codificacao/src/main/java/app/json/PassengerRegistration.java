package app.json;

import app.models.User;

import java.time.LocalDate;

public class PassengerRegistration {
    public String userName, password, name, surname, cpf, rg, telephone, email;
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
