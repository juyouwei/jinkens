package com.sgcc.common.serializer;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class EmptyStringSerializer extends JsonSerializer<Object> {
	public static final JsonSerializer<Object> INSTANCE = new EmptyStringSerializer();

    private EmptyStringSerializer() {

    }

    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString("");
    }
}
