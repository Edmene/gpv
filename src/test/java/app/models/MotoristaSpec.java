package app.models;

import org.javalite.activejdbc.test.DBSpec;
import org.junit.Test;

public class MotoristaSpec extends DBSpec {

    @Test
    public void shouldValidateRequiredAttributes(){
        Motorista motorista = new Motorista();
        a(motorista).shouldNotBe("valid");

        motorista.set("nome", "nada", "sobrenome", "fk",
                "rg", "123123123", "cnh", "12321321");
        a(motorista).shouldBe("valid");
    }
}
