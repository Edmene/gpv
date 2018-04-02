package app.models;

import org.javalite.activejdbc.test.DBSpec;
import org.junit.Test;

public class DriverSpec extends DBSpec {

    @Test
    public void shouldValidateRequiredAttributes(){
        Driver driver = new Driver();
        a(driver).shouldNotBe("valid");

        driver.set("nome", "nada", "sobrenome", "fk",
                "rg", "123123123", "cnh", "12321321");
        a(driver).shouldBe("valid");
    }
}
