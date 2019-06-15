package app.utils;

import app.enums.Shift;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class ShiftDeserializer extends StdDeserializer<Shift> {


    public ShiftDeserializer() {
        this(null);
    }

    private ShiftDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Shift deserialize(
            JsonParser jsonparser, DeserializationContext context)
            throws IOException {

        return Shift.values()[Integer.parseInt(jsonparser.getText())];
    }
}
