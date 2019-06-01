package app.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class JavaScriptDateSerializer extends StdSerializer<LocalDate> {


    public JavaScriptDateSerializer() {
        this(null);
    }

    private JavaScriptDateSerializer(Class<LocalDate> t) {
        super(t);
    }

    @Override
    public void serialize(
            LocalDate value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException {

        gen.writeString(value.atStartOfDay().toInstant(ZoneOffset.UTC).toString());
    }
}
