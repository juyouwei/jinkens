package com.sgcc.web.constant;
public class CodeConstant {

    /**
     * 成功code
     */
    public static final Integer REQUEST_SUCCESS_CODE = 1;
    /**
     * 参数错误
     */
    public static final Integer REQUEST_ERROR_PARAMS_CODE = 0;

    /**
     * 程序异常code
     */
    public static final Integer REQUEST_PROGRAM_ERROR_CODE = -100;

    /**
     * 鉴权失败
     */
    public static final Integer REQUEST_ERROR_AUTHENTICATION_CODE = -200;
    /**
     * 授权失败
     */
    public static final Integer REQUEST_ERROR_AUTHORIZE_CODE = -201;
    /**
     * 非法访问
     */
    public static final Integer REQUEST_ERROR_ILLEGALLY_CODE = -202;
    /**
     * IP拒绝code
     */
    public static final Integer REQUEST_REFUSE_IP_ERROR_CODE = -60;

}