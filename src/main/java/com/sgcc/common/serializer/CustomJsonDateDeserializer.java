package com.sgcc.common.serializer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomJsonDateDeserializer  extends JsonDeserializer<Date>{
	public static final String FORMAT_DATE_TIME_14 = "yyyy-MM-dd HH:mm:ss";
	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		SimpleDateFormat timeSDF14 = new SimpleDateFormat(FORMAT_DATE_TIME_14);
		String date = jp.getText();
        try {
            if(StringUtils.isBlank(date)) {
                return null;
            }
            return timeSDF14.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	}


