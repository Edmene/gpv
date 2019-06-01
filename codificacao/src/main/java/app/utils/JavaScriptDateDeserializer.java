package app.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.*;

public class JavaScriptDateDeserializer extends StdDeserializer<LocalDate> {


    public JavaScriptDateDeserializer() {
        this(null);
    }

    private JavaScriptDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDate deserialize(
            JsonParser jsonparser, DeserializationContext context)
            throws IOException {

        Instant instant = Instant.parse(jsonparser.getText());
        LocalDateTime result = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
        return result.toLocalDate();
    }
}
