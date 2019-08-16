package com.sgcc.common.serializer;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
@SuppressWarnings("serial")
@JacksonStdImpl
public class BigDecimalFormatJsonSerializer extends StdSerializer<BigDecimal>{

    public BigDecimalFormatJsonSerializer() {
    	 super(Object.class, false);
		// TODO Auto-generated constructor stub
	}

	private String param;


    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        String format = null;
        //是否为空
        if (value != null) {
            //格式化是否为空
            if (!"".equals(param)) {
                DecimalFormat decimalFormat = new DecimalFormat(param);
                format = decimalFormat.format(value);
            } else {
                format = value.setScale(2, BigDecimal.ROUND_HALF_UP).toString();//保留两位小数，四舍五入 ，如100.373->100.73 ,100.375->100.38, 100.376->100.38
            }
        }
        gen.writeString(format);
    }

}
