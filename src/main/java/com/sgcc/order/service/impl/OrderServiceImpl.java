package com.sgcc.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.sgcc.common.exception.BaseException;
import com.sgcc.common.exception.BaseExceptionEnum;
import com.sgcc.order.entity.JyRechargeRegMain;
import com.sgcc.order.entity.SaleOrder;
import com.sgcc.order.mapper.JyRechargeRegMainMapper;
import com.sgcc.order.mapper.SaleOrderMapper;
import com.sgcc.order.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private SaleOrderMapper saleOrderMapper;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@Autowired
	private JyRechargeRegMainMapper jyRechargeRegMainMapper;
	
	@Override
	public SaleOrder createOrder(String saleId) {
		// TODO Auto-generated method stub
		
		
		SaleOrder saleOrder = saleOrderMapper.selectByPrimaryKey(saleId);
		//同步订单中心
		transactionTemplate.execute(new TransactionCallback<Object>() {//声明式事务实现更细粒度的事务控制

			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			
		});
	
    return null;
	}

	@Override
	public SaleOrder save(SaleOrder saleOrder) {
		// TODO Auto-generated method stub
		if(null==saleOrder){
			throw new BaseException(BaseExceptionEnum.INVALID_REQ_PARAM);
		}
		return null;
	}

	@Override
	public SaleOrder findSaleOrder(String saleId) {
		// TODO Auto-generated method stub
		return saleOrderMapper.selectByPrimaryKey(saleId);
	}

	@Override
	public JyRechargeRegMain findJyRechargeRegMain(String sysId) {
		// TODO Auto-generated method stub
		return jyRechargeRegMainMapper.selectByPrimaryKey(sysId);
	}
}
