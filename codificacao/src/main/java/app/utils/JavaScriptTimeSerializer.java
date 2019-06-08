package app.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

public class JavaScriptTimeSerializer extends StdSerializer<LocalTime> {


    public JavaScriptTimeSerializer() {
        this(null);
    }

    private JavaScriptTimeSerializer(Class<LocalTime> t) {
        super(t);
    }

    @Override
    public void serialize(
            LocalTime value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException {

        gen.writeString(value.atDate(LocalDate.now()).toInstant(ZoneOffset.UTC).toString());
    }
}
