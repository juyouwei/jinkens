package com.sgcc.mqmessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sgcc.common.component.mq.AbstractMessageHandler;
@Component
public class OrderMessageHandler extends AbstractMessageHandler{
	@Value("${message-queue.topics}")
	private String topic;
	@Override
	public void handle(Object messageBody) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTopic() {
		// TODO Auto-generated method stub
		return this.topic;
	}

	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		return "4TEST";
	}

	

}
