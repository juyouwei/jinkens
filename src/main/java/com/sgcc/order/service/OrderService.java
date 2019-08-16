package com.sgcc.order.service;

import com.sgcc.order.entity.JyRechargeRegMain;
import com.sgcc.order.entity.SaleOrder;

public interface OrderService {
	
	
	public SaleOrder createOrder(String id);
	
	public SaleOrder save(SaleOrder saleOrder);
	
	public SaleOrder findSaleOrder(String saleId);
	
	public JyRechargeRegMain findJyRechargeRegMain(String sysId);
	
	
}
