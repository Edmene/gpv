package app.json;

import app.models.Driver;

public class DriverJson {
    public Integer key;
    public String name, surname, cnh, cpf, rg;

    public DriverJson(){}

    public DriverJson(String name, String surname, String cnh, String cpf, String rg){
        this.name = name;
        this.surname = surname;
        this.cnh = cnh;
        this.cpf = cpf;
        this.rg = rg;
    }

    public DriverJson(Driver driver){
        this.key = (Integer) driver.getId();
        this.name = driver.getString("name");
        this.surname = driver.getString("surname");
        this.cnh = driver.getString("cnh");
        this.cpf = driver.getString("cpf");
        this.rg = driver.getString("rg");
    }

    public void setAttributesOfDriver(Driver driver){
        driver.set("id", this.key,
                "name", this.name,
                "surname", this.surname,
                "cnh", this.cnh,
                "cpf", this.cpf,
                "rg", this.rg);
    }
}
