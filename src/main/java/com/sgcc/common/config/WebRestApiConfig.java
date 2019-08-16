package com.sgcc.common.config;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sgcc.common.serializer.CustomNullStringSerializerProvider;


@Configuration
public class WebRestApiConfig extends WebMvcConfigurerAdapter {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jsonMessageConverter = (MappingJackson2HttpMessageConverter) converter;
                ObjectMapper objectMapper = jsonMessageConverter.getObjectMapper();
                objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
                objectMapper.setSerializerProvider(new CustomNullStringSerializerProvider());
                objectMapper.setSerializationInclusion(Include.NON_NULL);
                objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

                SimpleModule bigDecimalModule = new SimpleModule("bigDecimalModule");
                objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);//ObjectMapper默认Number转换为Double,这里设置成bigdecimal
                //bigDecimalModule.addSerializer(BigDecimal.class, new BigDecimalFormatJsonSerializer());
                //在进出前后台的时候，设置BigDecimal和字符串之间转换
                bigDecimalModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);//这里使用ToStringSerializer序列化，其他方式会报错
                objectMapper.registerModule(bigDecimalModule);
                objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);//日期序列化为时间戳

            }
        }
        super.extendMessageConverters(converters);
    }
}
