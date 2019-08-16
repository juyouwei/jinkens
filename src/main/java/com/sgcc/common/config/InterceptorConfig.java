package com.sgcc.common.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.sgcc.common.component.DefaultInterceptor;
@Configuration
public  class InterceptorConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private DefaultInterceptor defaultInterceptor;
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(defaultInterceptor).addPathPatterns("/**");
    }
}
