package com.sgcc.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.Generated;

public class JyRechargeRegMain  implements Serializable{
   
	private static final long serialVersionUID = -4332210209213038402L;

	/**
     * 主键
     */
    private String sysId;

    /**
     * 用电户号
     */
    private String elecustomerNo;

    /**
     * 请求时间
     */
    private String reqTime;

    /**
     * 充值网省编号
     */
    private String provCode;

    /**
     * 用户标识-用户手机号
     */
    private String userIdentification;

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
     * 订单id
     */
    private String orderId;

    /**
     * 核对状态   HD000:核对无误 HD001：已冲正 HD002：核对有误
     */
    private String checkState;

    /**
     * 核对错误原因
     */
    private String reason;

    /**
     * 充值卡核对结果描述
     */
    private String czkCheckDescription;

    /**
     * 充值卡系统流水号
     */
    private String czkSysId;

    /**
     * 是否核对   1： 是 0：否 
     */
    private String isCheck;

    
	public JyRechargeRegMain() {
		super();
	}

	@Generated("SparkTools")
	private JyRechargeRegMain(Builder builder) {
		this.sysId = builder.sysId;
		this.elecustomerNo = builder.elecustomerNo;
		this.reqTime = builder.reqTime;
		this.provCode = builder.provCode;
		this.userIdentification = builder.userIdentification;
		this.totalAmont = builder.totalAmont;
		this.powerCompanyCode = builder.powerCompanyCode;
		this.powerCompanyNm = builder.powerCompanyNm;
		this.userName = builder.userName;
		this.userAddr = builder.userAddr;
		this.state = builder.state;
		this.updateTime = builder.updateTime;
		this.orderId = builder.orderId;
		this.checkState = builder.checkState;
		this.reason = builder.reason;
		this.czkCheckDescription = builder.czkCheckDescription;
		this.czkSysId = builder.czkSysId;
		this.isCheck = builder.isCheck;
	}

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
     * 用户标识-用户手机号
     * @return USER_IDENTIFICATION 用户标识-用户手机号
     */
    public String getUserIdentification() {
        return userIdentification;
    }

    /**
     * 用户标识-用户手机号
     * @param userIdentification 用户标识-用户手机号
     */
    public void setUserIdentification(String userIdentification) {
        this.userIdentification = userIdentification == null ? null : userIdentification.trim();
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
     * 订单id
     * @return ORDER_ID 订单id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 订单id
     * @param orderId 订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 核对状态   HD000:核对无误 HD001：已冲正 HD002：核对有误
     * @return CHECK_STATE 核对状态   HD000:核对无误 HD001：已冲正 HD002：核对有误
     */
    public String getCheckState() {
        return checkState;
    }

    /**
     * 核对状态   HD000:核对无误 HD001：已冲正 HD002：核对有误
     * @param checkState 核对状态   HD000:核对无误 HD001：已冲正 HD002：核对有误
     */
    public void setCheckState(String checkState) {
        this.checkState = checkState == null ? null : checkState.trim();
    }

    /**
     * 核对错误原因
     * @return REASON 核对错误原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 核对错误原因
     * @param reason 核对错误原因
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * 充值卡核对结果描述
     * @return CZK_CHECK_DESCRIPTION 充值卡核对结果描述
     */
    public String getCzkCheckDescription() {
        return czkCheckDescription;
    }

    /**
     * 充值卡核对结果描述
     * @param czkCheckDescription 充值卡核对结果描述
     */
    public void setCzkCheckDescription(String czkCheckDescription) {
        this.czkCheckDescription = czkCheckDescription == null ? null : czkCheckDescription.trim();
    }

    /**
     * 充值卡系统流水号
     * @return CZK_SYS_ID 充值卡系统流水号
     */
    public String getCzkSysId() {
        return czkSysId;
    }

    /**
     * 充值卡系统流水号
     * @param czkSysId 充值卡系统流水号
     */
    public void setCzkSysId(String czkSysId) {
        this.czkSysId = czkSysId == null ? null : czkSysId.trim();
    }

    /**
     * 是否核对   1： 是 0：否 
     * @return IS_CHECK 是否核对   1： 是 0：否 
     */
    public String getIsCheck() {
        return isCheck;
    }

    /**
     * 是否核对   1： 是 0：否 
     * @param isCheck 是否核对   1： 是 0：否 
     */
    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck == null ? null : isCheck.trim();
    }

	/**
	 * Creates builder to build {@link JyRechargeRegMain}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link JyRechargeRegMain}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String sysId;
		private String elecustomerNo;
		private String reqTime;
		private String provCode;
		private String userIdentification;
		private BigDecimal totalAmont;
		private String powerCompanyCode;
		private String powerCompanyNm;
		private String userName;
		private String userAddr;
		private String state;
		private String updateTime;
		private String orderId;
		private String checkState;
		private String reason;
		private String czkCheckDescription;
		private String czkSysId;
		private String isCheck;

		private Builder() {
		}

		public Builder withSysId(String sysId) {
			this.sysId = sysId;
			return this;
		}

		public Builder withElecustomerNo(String elecustomerNo) {
			this.elecustomerNo = elecustomerNo;
			return this;
		}

		public Builder withReqTime(String reqTime) {
			this.reqTime = reqTime;
			return this;
		}

		public Builder withProvCode(String provCode) {
			this.provCode = provCode;
			return this;
		}

		public Builder withUserIdentification(String userIdentification) {
			this.userIdentification = userIdentification;
			return this;
		}

		public Builder withTotalAmont(BigDecimal totalAmont) {
			this.totalAmont = totalAmont;
			return this;
		}

		public Builder withPowerCompanyCode(String powerCompanyCode) {
			this.powerCompanyCode = powerCompanyCode;
			return this;
		}

		public Builder withPowerCompanyNm(String powerCompanyNm) {
			this.powerCompanyNm = powerCompanyNm;
			return this;
		}

		public Builder withUserName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder withUserAddr(String userAddr) {
			this.userAddr = userAddr;
			return this;
		}

		public Builder withState(String state) {
			this.state = state;
			return this;
		}

		public Builder withUpdateTime(String updateTime) {
			this.updateTime = updateTime;
			return this;
		}

		public Builder withOrderId(String orderId) {
			this.orderId = orderId;
			return this;
		}

		public Builder withCheckState(String checkState) {
			this.checkState = checkState;
			return this;
		}

		public Builder withReason(String reason) {
			this.reason = reason;
			return this;
		}

		public Builder withCzkCheckDescription(String czkCheckDescription) {
			this.czkCheckDescription = czkCheckDescription;
			return this;
		}

		public Builder withCzkSysId(String czkSysId) {
			this.czkSysId = czkSysId;
			return this;
		}

		public Builder withIsCheck(String isCheck) {
			this.isCheck = isCheck;
			return this;
		}

		public JyRechargeRegMain build() {
			return new JyRechargeRegMain(this);
		}
	}
    
}