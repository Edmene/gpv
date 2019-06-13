package app.utils;

import app.enums.CalculationMethod;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

public class CalcMethodSerializer extends StdSerializer<CalculationMethod> {


    public CalcMethodSerializer() {
        this(null);
    }

    private CalcMethodSerializer(Class<CalculationMethod> t) {
        super(t);
    }

    @Override
    public void serialize(
            CalculationMethod value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException {

        gen.writeString(value.name());
    }
}
