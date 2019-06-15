package app.utils;

import app.enums.Direction;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class DirectionSerializer extends StdSerializer<Direction> {


    public DirectionSerializer() {
        this(null);
    }

    private DirectionSerializer(Class<Direction> t) {
        super(t);
    }

    @Override
    public void serialize(
            Direction value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException {

        gen.writeString(String.valueOf(value.ordinal()));
    }
}
