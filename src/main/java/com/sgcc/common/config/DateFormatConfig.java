package com.sgcc.common.config;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonComponent;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.StdDateFormat;

@JsonComponent
public class DateFormatConfig {
	@SuppressWarnings("serial")
	private static SimpleDateFormat dateFormat14 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") {
		@Override
		public Date parse(String source) throws ParseException {
			try {
				if (StringUtils.isEmpty(source)) {
					return null;
				}
				return super.parse(source);
			} catch (Exception e) {
				return new StdDateFormat().parse(source);
			}
		}
	};


	/**
	 * 日期格式化
	 */
	public static class DateJsonSerializer extends JsonSerializer<Date> {
		@Override
		public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException {
			// 格式化日期
			jsonGenerator.writeString(dateFormat14.format(date));
		}
	}

	/**
	 * 解析日期字符串
	 */
	public static class DateJsonDeserializer extends JsonDeserializer<Date> {
		@Override
		public Date deserialize(JsonParser jp, DeserializationContext deserializationContext)
				throws IOException, JsonProcessingException {
			String date = jp.getText();
	        try {
	            if(StringUtils.isBlank(date)) {
	                return null;
	            }
	            return dateFormat14.parse(date);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
		}
	}
}