package com.sgcc.common.exception;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -2659178833028322966L;
	private final BaseExceptionEnum baseExceptionEnum;
    public BaseException(BaseExceptionEnum baseExceptionEnum) {
        super(baseExceptionEnum.getErrorMsg());
        this.baseExceptionEnum = baseExceptionEnum;
    }
    public BaseExceptionEnum getBaseExceptionEnum() {
        return baseExceptionEnum;
    }

   public int getErrorCode(){
	   return baseExceptionEnum.getErrorCode();
   }
   public String getErrorMsg(){
	   return baseExceptionEnum.getErrorMsg();
   }
}
