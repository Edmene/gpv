package app.utils;

import app.enums.Day;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class DayDeserializer extends StdDeserializer<Day> {


    public DayDeserializer() {
        this(null);
    }

    private DayDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Day deserialize(
            JsonParser jsonparser, DeserializationContext context)
            throws IOException {

        return Day.values()[Integer.parseInt(jsonparser.getText())];
    }
}
