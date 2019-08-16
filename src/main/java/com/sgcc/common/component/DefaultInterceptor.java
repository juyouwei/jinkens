package com.sgcc.common.component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Component
public class DefaultInterceptor implements HandlerInterceptor{
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		LOGGER.info("requestUrI={}",requestURI);
		
		RequestPojo requestPojo = new RequestPojo();
		requestPojo.setCurrentTimeMillis(System.currentTimeMillis());
		requestPojo.setRequestURI(requestURI);
		//MQ发送请求消息
		LOGGER.info("requestUrl:{}-->count:{}",requestURI);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
     class  RequestPojo{
    	 private String requestURI;
    	 private Long currentTimeMillis;
    	 
		public String getRequestURI() {
			return requestURI;
		}
		public void setRequestURI(String requestURI) {
			this.requestURI = requestURI;
		}
		public Long getCurrentTimeMillis() {
			return currentTimeMillis;
		}
		public void setCurrentTimeMillis(Long currentTimeMillis) {
			this.currentTimeMillis = currentTimeMillis;
		}
    	 
     }
}
