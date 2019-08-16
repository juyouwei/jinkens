package com.sgcc.main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;



public class MqProducer {
	  private static Logger logger = LoggerFactory.getLogger(MqProducer.class);
	    public static void main(String[] args) {
	        DefaultMQProducer producer = new DefaultMQProducer("PID_TEST");
	        producer.setNamesrvAddr("127.0.0.1:9876");
	        try {
	            producer.start();
	            logger.info("producer启动成功");
	            for (int i = 0; i < 5; i++) {
	                Message msg = new Message("mq_test", "tagA", "OrderID188", "Hello world".getBytes());
	                SendResult result = producer.send(msg);
	                logger.info("id：" + result.getMsgId() + " result:" + result.getSendStatus());
	            }
	        } catch (Exception e) {
	        	e.printStackTrace();
	            logger.error("发送消息失败，Exception error：" + e);
	        } finally {
	            producer.shutdown();
	        }
	    }
	
}
