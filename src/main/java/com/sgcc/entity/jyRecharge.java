package com.sgcc.entity;

import java.math.BigDecimal;

public class jyRecharge {
    /**
     * 主键
     */
    private String sysId;

    /**
     * 用电户号
     */
    private String elecustomerNo;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 密码
     */
    private String cardPassword;

    /**
     * 请求时间
     */
    private String reqTime;

    /**
     * 充值网省编号
     */
    private String provCode;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 充值金额
     */
    private BigDecimal totalAmont;

    /**
     * 供电单位编码
     */
    private String powerCompanyCode;

    /**
     * 供电单位名称
     */
    private String powerCompanyNm;

    /**
     * 用电户名称
     */
    private String userName;

    /**
     * 用电户地址
     */
    private String userAddr;

    /**
     * 状态  00 成功  01 失败 02 未明 03其他
     */
    private String state;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 充值卡充值结果描述
     */
    private String resultDescription;

    /**
     * 主键
     * @return SYS_ID 主键
     */
    public String getSysId() {
        return sysId;
    }

    /**
     * 主键
     * @param sysId 主键
     */
    public void setSysId(String sysId) {
        this.sysId = sysId == null ? null : sysId.trim();
    }

    /**
     * 用电户号
     * @return ELECUSTOMER_NO 用电户号
     */
    public String getElecustomerNo() {
        return elecustomerNo;
    }

    /**
     * 用电户号
     * @param elecustomerNo 用电户号
     */
    public void setElecustomerNo(String elecustomerNo) {
        this.elecustomerNo = elecustomerNo == null ? null : elecustomerNo.trim();
    }

    /**
     * 卡号
     * @return CARD_NO 卡号
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 卡号
     * @param cardNo 卡号
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    /**
     * 密码
     * @return CARD_PASSWORD 密码
     */
    public String getCardPassword() {
        return cardPassword;
    }

    /**
     * 密码
     * @param cardPassword 密码
     */
    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword == null ? null : cardPassword.trim();
    }

    /**
     * 请求时间
     * @return REQ_TIME 请求时间
     */
    public String getReqTime() {
        return reqTime;
    }

    /**
     * 请求时间
     * @param reqTime 请求时间
     */
    public void setReqTime(String reqTime) {
        this.reqTime = reqTime == null ? null : reqTime.trim();
    }

    /**
     * 充值网省编号
     * @return PROV_CODE 充值网省编号
     */
    public String getProvCode() {
        return provCode;
    }

    /**
     * 充值网省编号
     * @param provCode 充值网省编号
     */
    public void setProvCode(String provCode) {
        this.provCode = provCode == null ? null : provCode.trim();
    }

    /**
     * 用户手机号
     * @return MOBILE 用户手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 用户手机号
     * @param mobile 用户手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 充值金额
     * @return TOTAL_AMONT 充值金额
     */
    public BigDecimal getTotalAmont() {
        return totalAmont;
    }

    /**
     * 充值金额
     * @param totalAmont 充值金额
     */
    public void setTotalAmont(BigDecimal totalAmont) {
        this.totalAmont = totalAmont;
    }

    /**
     * 供电单位编码
     * @return POWER_COMPANY_CODE 供电单位编码
     */
    public String getPowerCompanyCode() {
        return powerCompanyCode;
    }

    /**
     * 供电单位编码
     * @param powerCompanyCode 供电单位编码
     */
    public void setPowerCompanyCode(String powerCompanyCode) {
        this.powerCompanyCode = powerCompanyCode == null ? null : powerCompanyCode.trim();
    }

    /**
     * 供电单位名称
     * @return POWER_COMPANY_NM 供电单位名称
     */
    public String getPowerCompanyNm() {
        return powerCompanyNm;
    }

    /**
     * 供电单位名称
     * @param powerCompanyNm 供电单位名称
     */
    public void setPowerCompanyNm(String powerCompanyNm) {
        this.powerCompanyNm = powerCompanyNm == null ? null : powerCompanyNm.trim();
    }

    /**
     * 用电户名称
     * @return USER_NAME 用电户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用电户名称
     * @param userName 用电户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 用电户地址
     * @return USER_ADDR 用电户地址
     */
    public String getUserAddr() {
        return userAddr;
    }

    /**
     * 用电户地址
     * @param userAddr 用电户地址
     */
    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr == null ? null : userAddr.trim();
    }

    /**
     * 状态  00 成功  01 失败 02 未明 03其他
     * @return STATE 状态  00 成功  01 失败 02 未明 03其他
     */
    public String getState() {
        return state;
    }

    /**
     * 状态  00 成功  01 失败 02 未明 03其他
     * @param state 状态  00 成功  01 失败 02 未明 03其他
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    /**
     * 充值卡充值结果描述
     * @return RESULT_DESCRIPTION 充值卡充值结果描述
     */
    public String getResultDescription() {
        return resultDescription;
    }

    /**
     * 充值卡充值结果描述
     * @param resultDescription 充值卡充值结果描述
     */
    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription == null ? null : resultDescription.trim();
    }
}