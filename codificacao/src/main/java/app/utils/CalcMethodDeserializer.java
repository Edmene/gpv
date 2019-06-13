package app.utils;

import app.enums.CalculationMethod;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.*;

public class CalcMethodDeserializer extends StdDeserializer<CalculationMethod> {


    public CalcMethodDeserializer() {
        this(null);
    }

    private CalcMethodDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CalculationMethod deserialize(
            JsonParser jsonparser, DeserializationContext context)
            throws IOException {

        return CalculationMethod.valueOf(jsonparser.getText());
    }
}
