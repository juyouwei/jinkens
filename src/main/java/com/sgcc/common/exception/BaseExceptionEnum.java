package com.sgcc.common.exception;

public enum BaseExceptionEnum  {
	
	APPLICATION_ERROR(-1000, "网络繁忙,请稍后再试"),
    INVALID_USER(-1001, "token校验出错"),
    INVALID_REQ_PARAM(-1002, "参数错误"),
    REQUEST_REFUSE_IP_ERROR_CODE(-1003,"IP拒绝")
    ;
	BaseExceptionEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    private final Integer errorCode;
    private final String errorMsg;
	public Integer getErrorCode() {
		return errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}



}
