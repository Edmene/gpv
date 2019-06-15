package app.utils;

import app.enums.Direction;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class DirectionDeserializer extends StdDeserializer<Direction> {


    public DirectionDeserializer() {
        this(null);
    }

    private DirectionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Direction deserialize(
            JsonParser jsonparser, DeserializationContext context)
            throws IOException {

        return Direction.values()[Integer.parseInt(jsonparser.getText())];
    }
}
