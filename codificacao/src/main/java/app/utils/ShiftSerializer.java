package app.utils;

import app.enums.Shift;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ShiftSerializer extends StdSerializer<Shift> {


    public ShiftSerializer() {
        this(null);
    }

    private ShiftSerializer(Class<Shift> t) {
        super(t);
    }

    @Override
    public void serialize(
            Shift value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException {

        gen.writeString(String.valueOf(value.ordinal()));
    }
}
