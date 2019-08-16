package com.sgcc.order.entity;

import com.sgcc.order.enums.ShipStatus;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.Generated;

public class SaleOrder implements Serializable{
   
	private static final long serialVersionUID = -2811074188851931771L;

	/**
     * 销售订单号
     */
    private String saleId;

    /**
     * 渠道编号               
     */
    private String channelCode;

    /**
     * 渠道名称         
     */
    private String channelName;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 积分抵扣金额
     */
    private BigDecimal pointsAmount;

    /**
     * 优惠券抵扣金额
     */
    private BigDecimal couponAmount;

    /**
     * 红包金额
     */
    private BigDecimal walletAmount;

    /**
     * 卡券抵扣金额
     */
    private BigDecimal powerWalducAmount;

    /**
     * 应付金额
     */
    private BigDecimal payAmount;

    /**
     * 发货状态 01：未发货 02：发货中（保单未明） 03：已发货 04：发货失败 
     */
    private ShipStatus shipStatus;

    /**
     * 订单状态 01：未完成 02：已完成 03：已失败 99：已关闭 
     */
    private String orderStatus;

    /**
     * 付款状态 01：未付款 02：已付款 
     */
    private String payStatus;

    /**
     * 支付订单号
     */
    private String payOrderId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 购买客户编号
     */
    private String userId;

    /**
     * 购买客户类型 01：个人 02：企业
     */
    private String userType;

    /**
     * 对账状态 1：未对账 3：已对账 
     */
    private String chkStat;

    /**
     * 销售退款单号
     */
    private String saleRtnNo;

    /**
     * 完成时间
     */
    private String completeTime;

    /**
     * 支付完成时间
     */
    private String payComStime;

    /**
     * 卡券使用数量
     */
    private String ticketNum;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 付款流水号
     */
    private String salePayId;

    /**
     * 账单明细编号
     */
    private String unBillDetailId;

    /**
     * 订单关闭时间
     */
    private String closeTime;

    /**
     * 充值卡金额
     */
    private BigDecimal rechargeAmount;

    /**
     * 积分抵扣数量
     */
    private String pointsNum;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 满减金额
     */
    private BigDecimal reduceAmount;

    
	public SaleOrder() {
		super();
	}

	@Generated("SparkTools")
	private SaleOrder(Builder builder) {
		this.saleId = builder.saleId;
		this.channelCode = builder.channelCode;
		this.channelName = builder.channelName;
		this.orderAmount = builder.orderAmount;
		this.pointsAmount = builder.pointsAmount;
		this.couponAmount = builder.couponAmount;
		this.walletAmount = builder.walletAmount;
		this.powerWalducAmount = builder.powerWalducAmount;
		this.payAmount = builder.payAmount;
		this.shipStatus = builder.shipStatus;
		this.orderStatus = builder.orderStatus;
		this.payStatus = builder.payStatus;
		this.payOrderId = builder.payOrderId;
		this.createTime = builder.createTime;
		this.userId = builder.userId;
		this.userType = builder.userType;
		this.chkStat = builder.chkStat;
		this.saleRtnNo = builder.saleRtnNo;
		this.completeTime = builder.completeTime;
		this.payComStime = builder.payComStime;
		this.ticketNum = builder.ticketNum;
		this.updateTime = builder.updateTime;
		this.salePayId = builder.salePayId;
		this.unBillDetailId = builder.unBillDetailId;
		this.closeTime = builder.closeTime;
		this.rechargeAmount = builder.rechargeAmount;
		this.pointsNum = builder.pointsNum;
		this.goodsCode = builder.goodsCode;
		this.reduceAmount = builder.reduceAmount;
	}

    /**
     * 销售订单号
     * @return SALE_ID 销售订单号
     */
    public String getSaleId() {
        return saleId;
    }

    /**
     * 销售订单号
     * @param saleId 销售订单号
     */
    public void setSaleId(String saleId) {
        this.saleId = saleId == null ? null : saleId.trim();
    }

    /**
     * 渠道编号               
     * @return CHANNEL_CODE 渠道编号               
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * 渠道编号               
     * @param channelCode 渠道编号               
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    /**
     * 渠道名称         
     * @return CHANNEL_NAME 渠道名称         
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 渠道名称         
     * @param channelName 渠道名称         
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    /**
     * 订单金额
     * @return ORDER_AMOUNT 订单金额
     */
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     * 订单金额
     * @param orderAmount 订单金额
     */
    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * 积分抵扣金额
     * @return POINTS_AMOUNT 积分抵扣金额
     */
    public BigDecimal getPointsAmount() {
        return pointsAmount;
    }

    /**
     * 积分抵扣金额
     * @param pointsAmount 积分抵扣金额
     */
    public void setPointsAmount(BigDecimal pointsAmount) {
        this.pointsAmount = pointsAmount;
    }

    /**
     * 优惠券抵扣金额
     * @return COUPON_AMOUNT 优惠券抵扣金额
     */
    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    /**
     * 优惠券抵扣金额
     * @param couponAmount 优惠券抵扣金额
     */
    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    /**
     * 红包金额
     * @return WALLET_AMOUNT 红包金额
     */
    public BigDecimal getWalletAmount() {
        return walletAmount;
    }

    /**
     * 红包金额
     * @param walletAmount 红包金额
     */
    public void setWalletAmount(BigDecimal walletAmount) {
        this.walletAmount = walletAmount;
    }

    /**
     * 卡券抵扣金额
     * @return POWER_WALDUC_AMOUNT 卡券抵扣金额
     */
    public BigDecimal getPowerWalducAmount() {
        return powerWalducAmount;
    }

    /**
     * 卡券抵扣金额
     * @param powerWalducAmount 卡券抵扣金额
     */
    public void setPowerWalducAmount(BigDecimal powerWalducAmount) {
        this.powerWalducAmount = powerWalducAmount;
    }

    /**
     * 应付金额
     * @return PAY_AMOUNT 应付金额
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 应付金额
     * @param payAmount 应付金额
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 发货状态 01：未发货 02：发货中（保单未明） 03：已发货 04：发货失败 
     * @return SHIP_STATUS 发货状态 01：未发货 02：发货中（保单未明） 03：已发货 04：发货失败 
     */
    public ShipStatus getShipStatus() {
        return shipStatus;
    }

    /**
     * 发货状态 01：未发货 02：发货中（保单未明） 03：已发货 04：发货失败 
     * @param shipStatus 发货状态 01：未发货 02：发货中（保单未明） 03：已发货 04：发货失败 
     */
    public void setShipStatus(ShipStatus shipStatus) {
        this.shipStatus = shipStatus == null ? null : shipStatus;
    }

    /**
     * 订单状态 01：未完成 02：已完成 03：已失败 99：已关闭 
     * @return ORDER_STATUS 订单状态 01：未完成 02：已完成 03：已失败 99：已关闭 
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * 订单状态 01：未完成 02：已完成 03：已失败 99：已关闭 
     * @param orderStatus 订单状态 01：未完成 02：已完成 03：已失败 99：已关闭 
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    /**
     * 付款状态 01：未付款 02：已付款 
     * @return PAY_STATUS 付款状态 01：未付款 02：已付款 
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * 付款状态 01：未付款 02：已付款 
     * @param payStatus 付款状态 01：未付款 02：已付款 
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    /**
     * 支付订单号
     * @return PAY_ORDER_ID 支付订单号
     */
    public String getPayOrderId() {
        return payOrderId;
    }

    /**
     * 支付订单号
     * @param payOrderId 支付订单号
     */
    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId == null ? null : payOrderId.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * 购买客户编号
     * @return USER_ID 购买客户编号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 购买客户编号
     * @param userId 购买客户编号
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 购买客户类型 01：个人 02：企业
     * @return USER_TYPE 购买客户类型 01：个人 02：企业
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 购买客户类型 01：个人 02：企业
     * @param userType 购买客户类型 01：个人 02：企业
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     * 对账状态 1：未对账 3：已对账 
     * @return CHK_STAT 对账状态 1：未对账 3：已对账 
     */
    public String getChkStat() {
        return chkStat;
    }

    /**
     * 对账状态 1：未对账 3：已对账 
     * @param chkStat 对账状态 1：未对账 3：已对账 
     */
    public void setChkStat(String chkStat) {
        this.chkStat = chkStat == null ? null : chkStat.trim();
    }

    /**
     * 销售退款单号
     * @return SALE_RTN_NO 销售退款单号
     */
    public String getSaleRtnNo() {
        return saleRtnNo;
    }

    /**
     * 销售退款单号
     * @param saleRtnNo 销售退款单号
     */
    public void setSaleRtnNo(String saleRtnNo) {
        this.saleRtnNo = saleRtnNo == null ? null : saleRtnNo.trim();
    }

    /**
     * 完成时间
     * @return COMPLETE_TIME 完成时间
     */
    public String getCompleteTime() {
        return completeTime;
    }

    /**
     * 完成时间
     * @param completeTime 完成时间
     */
    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime == null ? null : completeTime.trim();
    }

    /**
     * 支付完成时间
     * @return PAY_COM_STIME 支付完成时间
     */
    public String getPayComStime() {
        return payComStime;
    }

    /**
     * 支付完成时间
     * @param payComStime 支付完成时间
     */
    public void setPayComStime(String payComStime) {
        this.payComStime = payComStime == null ? null : payComStime.trim();
    }

    /**
     * 卡券使用数量
     * @return TICKET_NUM 卡券使用数量
     */
    public String getTicketNum() {
        return ticketNum;
    }

    /**
     * 卡券使用数量
     * @param ticketNum 卡券使用数量
     */
    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum == null ? null : ticketNum.trim();
    }

    /**
     * 修改时间
     * @return UPDATE_TIME 修改时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    /**
     * 付款流水号
     * @return SALE_PAY_ID 付款流水号
     */
    public String getSalePayId() {
        return salePayId;
    }

    /**
     * 付款流水号
     * @param salePayId 付款流水号
     */
    public void setSalePayId(String salePayId) {
        this.salePayId = salePayId == null ? null : salePayId.trim();
    }

    /**
     * 账单明细编号
     * @return UN_BILL_DETAIL_ID 账单明细编号
     */
    public String getUnBillDetailId() {
        return unBillDetailId;
    }

    /**
     * 账单明细编号
     * @param unBillDetailId 账单明细编号
     */
    public void setUnBillDetailId(String unBillDetailId) {
        this.unBillDetailId = unBillDetailId == null ? null : unBillDetailId.trim();
    }

    /**
     * 订单关闭时间
     * @return CLOSE_TIME 订单关闭时间
     */
    public String getCloseTime() {
        return closeTime;
    }

    /**
     * 订单关闭时间
     * @param closeTime 订单关闭时间
     */
    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime == null ? null : closeTime.trim();
    }

    /**
     * 充值卡金额
     * @return RECHARGE_AMOUNT 充值卡金额
     */
    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    /**
     * 充值卡金额
     * @param rechargeAmount 充值卡金额
     */
    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    /**
     * 积分抵扣数量
     * @return POINTS_NUM 积分抵扣数量
     */
    public String getPointsNum() {
        return pointsNum;
    }

    /**
     * 积分抵扣数量
     * @param pointsNum 积分抵扣数量
     */
    public void setPointsNum(String pointsNum) {
        this.pointsNum = pointsNum == null ? null : pointsNum.trim();
    }

    /**
     * 商品编码
     * @return GOODS_CODE 商品编码
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 商品编码
     * @param goodsCode 商品编码
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    /**
     * 满减金额
     * @return REDUCE_AMOUNT 满减金额
     */
    public BigDecimal getReduceAmount() {
        return reduceAmount;
    }

    /**
     * 满减金额
     * @param reduceAmount 满减金额
     */
    public void setReduceAmount(BigDecimal reduceAmount) {
        this.reduceAmount = reduceAmount;
    }

	/**
	 * Creates builder to build {@link SaleOrder}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link SaleOrder}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String saleId;
		private String channelCode;
		private String channelName;
		private BigDecimal orderAmount;
		private BigDecimal pointsAmount;
		private BigDecimal couponAmount;
		private BigDecimal walletAmount;
		private BigDecimal powerWalducAmount;
		private BigDecimal payAmount;
		private ShipStatus shipStatus;
		private String orderStatus;
		private String payStatus;
		private String payOrderId;
		private String createTime;
		private String userId;
		private String userType;
		private String chkStat;
		private String saleRtnNo;
		private String completeTime;
		private String payComStime;
		private String ticketNum;
		private String updateTime;
		private String salePayId;
		private String unBillDetailId;
		private String closeTime;
		private BigDecimal rechargeAmount;
		private String pointsNum;
		private String goodsCode;
		private BigDecimal reduceAmount;

		private Builder() {
		}

		public Builder withSaleId(String saleId) {
			this.saleId = saleId;
			return this;
		}

		public Builder withChannelCode(String channelCode) {
			this.channelCode = channelCode;
			return this;
		}

		public Builder withChannelName(String channelName) {
			this.channelName = channelName;
			return this;
		}

		public Builder withOrderAmount(BigDecimal orderAmount) {
			this.orderAmount = orderAmount;
			return this;
		}

		public Builder withPointsAmount(BigDecimal pointsAmount) {
			this.pointsAmount = pointsAmount;
			return this;
		}

		public Builder withCouponAmount(BigDecimal couponAmount) {
			this.couponAmount = couponAmount;
			return this;
		}

		public Builder withWalletAmount(BigDecimal walletAmount) {
			this.walletAmount = walletAmount;
			return this;
		}

		public Builder withPowerWalducAmount(BigDecimal powerWalducAmount) {
			this.powerWalducAmount = powerWalducAmount;
			return this;
		}

		public Builder withPayAmount(BigDecimal payAmount) {
			this.payAmount = payAmount;
			return this;
		}

		public Builder withShipStatus(ShipStatus shipStatus) {
			this.shipStatus = shipStatus;
			return this;
		}

		public Builder withOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
			return this;
		}

		public Builder withPayStatus(String payStatus) {
			this.payStatus = payStatus;
			return this;
		}

		public Builder withPayOrderId(String payOrderId) {
			this.payOrderId = payOrderId;
			return this;
		}

		public Builder withCreateTime(String createTime) {
			this.createTime = createTime;
			return this;
		}

		public Builder withUserId(String userId) {
			this.userId = userId;
			return this;
		}

		public Builder withUserType(String userType) {
			this.userType = userType;
			return this;
		}

		public Builder withChkStat(String chkStat) {
			this.chkStat = chkStat;
			return this;
		}

		public Builder withSaleRtnNo(String saleRtnNo) {
			this.saleRtnNo = saleRtnNo;
			return this;
		}

		public Builder withCompleteTime(String completeTime) {
			this.completeTime = completeTime;
			return this;
		}

		public Builder withPayComStime(String payComStime) {
			this.payComStime = payComStime;
			return this;
		}

		public Builder withTicketNum(String ticketNum) {
			this.ticketNum = ticketNum;
			return this;
		}

		public Builder withUpdateTime(String updateTime) {
			this.updateTime = updateTime;
			return this;
		}

		public Builder withSalePayId(String salePayId) {
			this.salePayId = salePayId;
			return this;
		}

		public Builder withUnBillDetailId(String unBillDetailId) {
			this.unBillDetailId = unBillDetailId;
			return this;
		}

		public Builder withCloseTime(String closeTime) {
			this.closeTime = closeTime;
			return this;
		}

		public Builder withRechargeAmount(BigDecimal rechargeAmount) {
			this.rechargeAmount = rechargeAmount;
			return this;
		}

		public Builder withPointsNum(String pointsNum) {
			this.pointsNum = pointsNum;
			return this;
		}

		public Builder withGoodsCode(String goodsCode) {
			this.goodsCode = goodsCode;
			return this;
		}

		public Builder withReduceAmount(BigDecimal reduceAmount) {
			this.reduceAmount = reduceAmount;
			return this;
		}

		public SaleOrder build() {
			return new SaleOrder(this);
		}
	}
    
}