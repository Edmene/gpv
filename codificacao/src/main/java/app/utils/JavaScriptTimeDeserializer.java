package app.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.*;

public class JavaScriptTimeDeserializer extends StdDeserializer<LocalTime> {


    public JavaScriptTimeDeserializer() {
        this(null);
    }

    private JavaScriptTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalTime deserialize(
            JsonParser jsonparser, DeserializationContext context)
            throws IOException {

        Instant instant = Instant.parse(jsonparser.getText());
        LocalDateTime result = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
        return result.toLocalTime();
    }
}
