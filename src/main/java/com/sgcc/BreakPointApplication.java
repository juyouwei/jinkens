package com.sgcc;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.misc.BASE64Decoder;
@SpringBootApplication(scanBasePackages={"com.sgcc"})
@MapperScan(basePackages={"com.sgcc.*.mapper","com.sgcc.**.mapper"})
public class BreakPointApplication {
public static void main(String[] args) {
	    InputStream inputStream = BreakPointApplication.class.getClassLoader().getResourceAsStream("application.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					
				}
			}
		}
	  BASE64Decoder decoder = new BASE64Decoder();
		try {
			
			if (!StringUtils.isBlank(properties.getProperty("jasypt.encryptor.secretcode"))) {
				properties.put(new String(decoder.decodeBuffer("amFzeXB0LmVuY3J5cHRvci5wYXNzd29yZA==")),
						properties.getProperty("jasypt.encryptor.secretcode"));
			}
//			if (!StringUtils.isBlank(properties.getProperty("spring.datasource.secretcode"))) {
//				properties.put(new String(decoder.decodeBuffer("c3ByaW5nLmRhdGFzb3VyY2UucGFzc3dvcmQ=")),
//						properties.getProperty("spring.datasource.secretcode"));
//			}
			if (!StringUtils.isBlank(properties.getProperty("spring.redis.secretcode"))) {
				properties.put(new String(decoder.decodeBuffer("c3ByaW5nLnJlZGlzLnBhc3N3b3Jk")),
						properties.getProperty("spring.redis.secretcode"));
			}
		} catch (IOException e) {
		} finally {
			decoder = null;
		}
	  SpringApplication application = new SpringApplication(BreakPointApplication.class);
	  application.setDefaultProperties(properties);
	  application.run(args);
}
}
