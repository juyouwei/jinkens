package com.sgcc.common.serializer;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;

@SuppressWarnings("serial")
public class CustomNullStringSerializerProvider extends DefaultSerializerProvider{
	public CustomNullStringSerializerProvider() {
        super();
    }

    public CustomNullStringSerializerProvider(CustomNullStringSerializerProvider ssp, SerializationConfig config, SerializerFactory slf) {
        super(ssp, config, slf);
    }

    public DefaultSerializerProvider createInstance(SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        return new CustomNullStringSerializerProvider(this, serializationConfig, serializerFactory);
    }

    @Override
    public JsonSerializer<Object> findNullValueSerializer(BeanProperty property) throws JsonMappingException {
        if (property.getType().getRawClass().equals(String.class)) {
            return EmptyStringSerializer.INSTANCE;
        } else {
            return super.findNullValueSerializer(property);
        }
    }
}
