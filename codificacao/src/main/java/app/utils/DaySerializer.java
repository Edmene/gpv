package app.utils;

import app.enums.Day;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class DaySerializer extends StdSerializer<Day> {


    public DaySerializer() {
        this(null);
    }

    private DaySerializer(Class<Day> t) {
        super(t);
    }

    @Override
    public void serialize(
            Day value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException {

        gen.writeString(String.valueOf(value.ordinal()));
    }
}
