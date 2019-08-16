package com.sgcc.mqmessage.enums;

import com.sgcc.common.CommonEnum;

public enum BusinessType2 implements CommonEnum{

	COUPON("01","优惠券"),
	WALLET("02","红包"),
	POINTS("03","积分"),
	RECHAREG_CARD("04","充值卡");

	final String code;
	final String desc;
	
	private BusinessType2(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return code;
	}
	
}
