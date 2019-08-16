package com.sgcc.web;
import java.io.Serializable;
import com.sgcc.web.constant.CodeConstant;
public class ResponseResult<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer code;
	
	private String message;
	
	private T data;
	
    public ResponseResult(){

    }

    public ResponseResult(T data) {
        this(CodeConstant.REQUEST_SUCCESS_CODE, "成功", data);
    }

    public ResponseResult(Integer code, String message) {
        this(code, message, null);
    }

    public ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<T>(data);
    }

    public static <T> ResponseResult<T> fail(Integer code, String message, T data) {
        return new ResponseResult<T>(code, message,data);
    }

    public static <T> ResponseResult<T> fail(Integer code, String message) {
        return new ResponseResult<T>(code, message);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
