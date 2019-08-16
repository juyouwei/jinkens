package com.sgcc.web;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.UndeclaredThrowableException;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.google.gson.Gson;
import com.sgcc.common.exception.BaseException;

@ControllerAdvice
public class APIExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(APIExceptionHandler.class);

	private final MessageSource messageSource;
	@Autowired
    public APIExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
	@ExceptionHandler(value = { Exception.class })
	public void defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Locale locale,
			Exception ex) throws IOException {
		ResponseResult<Object> responseResult=new ResponseResult<>();
		Throwable exception = ex;
		int code = 500;
	    String errorMessage = null;
		if (ex instanceof UndeclaredThrowableException) {
			exception = ((UndeclaredThrowableException) ex).getUndeclaredThrowable();
		}
		if(ex instanceof BaseException){
			 code = ((BaseException)ex).getErrorCode();
	         errorMessage = ((BaseException) ex).getErrorMsg();
		}
		 // 如果异常里已经包含了错误信息，则不会再通过错误码获取预先定义的错误信息
        if (StringUtils.isBlank(errorMessage)) {
            String prefix = exception.getClass().getSimpleName();
            errorMessage = getMessage(prefix + "." + code, locale);
            if (errorMessage == null) {
                errorMessage = getMessage(Integer.toString(code), locale);
            }
        }
        responseResult.setCode(code);
        responseResult.setMessage(errorMessage);
        output(response, responseResult);

        if (!(ex instanceof BaseException) ) {
            LOGGER.error("Unknown Exception, URI = " + request.getRequestURI(), ex);
        } else {
            LOGGER.error("URI = {}, errorCode = {}, errorMessage = {}", request.getRequestURI(), code, errorMessage);
        }
    }

    private void output(HttpServletResponse response, ResponseResult<Object> result) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = new Gson().toJson(result);
        response.getWriter().write(json);
        response.flushBuffer();
    }

    private String getMessage(String key, Locale locale) {
        String errorMessage = null;
        try {
            errorMessage = messageSource.getMessage(key, null, locale);
        } catch (NoSuchMessageException exception) {
            LOGGER.debug("ErrorMessage|NotFound|{}", key);
        }
        return errorMessage;
    }
	}


