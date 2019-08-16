package com.sgcc.common.component.mq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.sgcc.common.component.mq.ConsumerTransactionMessage;
import com.sgcc.common.component.mq.mapper.ConsumerTransactionMessageMapper;
@Service
public class ConsumerTransactionMessageService {

	@Autowired
	private ConsumerTransactionMessageMapper consumerTransactionMessageMapper;
	
	
	@Async
	public void save(ConsumerTransactionMessage consumerTransactionMessage){
		consumerTransactionMessageMapper.insertSelective(consumerTransactionMessage);
	}
}
