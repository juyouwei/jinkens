package com.sgcc.order.enums;

import com.sgcc.common.CommonEnum;

public enum ShipStatus implements CommonEnum {
  
	
	NOT_SEND_GOODS("01","未发货"),
	SENDING_GOODS("02","发货中"),
	SENDED_GOODS("03","已发货"),
	SEND_FAILED("04","发货失败");
	
	final String code;
	final String desc;
	private ShipStatus(String code, String desc) {
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
