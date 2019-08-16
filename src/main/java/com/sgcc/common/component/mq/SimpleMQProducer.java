package com.sgcc.common.component.mq;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.sgcc.common.component.mq.mapper.ProducerTransactionMessageMapper;
import com.sgcc.common.spring.SpringContextUtils;
import com.sgcc.utils.TimeUtil;

/**
 * @version 1.0
 */

public class SimpleMQProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger("aliyun-mq-logger");

	private final Producer producer;

	public SimpleMQProducer(Producer producer) {
		this.producer = producer;
	}

	public void send(String topic, String tag, MQMessage message) {
		sendDelayMessage(topic, tag, message, 0);
	}

	/**
	 * 延时投递, delay为milliseconds
	 *
	 * @param topic
	 *            主题
	 * @param tag
	 *            标签
	 * @param message
	 *            消息体
	 * @param delay
	 *            延时时间，毫秒0
	 */
	public void sendDelayMessage(String topic, String tag, MQMessage message, long delay) {

		LOGGER.info("MQ|SEND|{}|{}|{}|{}", topic, tag, message.getMessageKey(), delay);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (ObjectOutputStream oStream = new ObjectOutputStream(baos)) {
			oStream.writeObject(message.getData());
		} catch (Exception e) {
			LOGGER.error("MQ|SEND|M2O|FAILED", e);
		}
		Message msg = new Message(topic, tag, baos.toByteArray());
		if (delay > 0) {
			msg.setStartDeliverTime(System.currentTimeMillis() + delay);
		}
		msg.setKey(message.getMessageKey());
		ProducerTransactionMessage producerTransactionMessage = ProducerTransactionMessage.builder()
                .withMessageStatus(MQMessageStatus.UNCONSUMED.toString())
                .withBody(JSON.toJSONString(message.getData()))
                .withMesssageId(message.getMessageKey())
                .withTopic(topic)
                .withCreateTime(TimeUtil.getCurrentTime17ByMillis())
                .withSendTime(TimeUtil.getCurrentTime17ByMillis())
                .withUpdateTime("")
                .withTag(tag)
                .build();
			  try {
				ProducerTransactionMessageMapper producerTransactionMessageMapper = SpringContextUtils.getBean(ProducerTransactionMessageMapper.class);
				producerTransactionMessageMapper.insert(producerTransactionMessage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			producer.sendOneway(msg);
		} catch (Exception e) {
			LOGGER.error("MQ|SEND|FAILED", e);
		}
	}

	/**
	 * 在事务成功提交后，发送消息
	 *
	 * @param topic
	 *            主题
	 * @param tag
	 *            标签
	 * @param message
	 *            消息体
	 */
	public void sendAfterTransactionCommit(String topic, String tag, MQMessage message) {
		if (!TransactionSynchronizationManager.isSynchronizationActive()) {
			LOGGER.info("Transaction synchronization is NOT ACTIVE. Executing right now, ONS Topic {}, Tag {} ", topic,
					tag);
			send(topic, tag, message);
			return;
		}
		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
			@Override
			public void afterCommit() {
				send(topic, tag, message);
			}
		});
	}

	/**
	 * 在事务成功提交后，延时投递, delay为milliseconds
	 *
	 * @param topic
	 *            主题
	 * @param tag
	 *            标签
	 * @param message
	 *            消息体
	 * @param delay
	 *            延时时间，毫秒0
	 */
	public void sendDelayMessageAfterTransactionCommit(String topic, String tag, MQMessage message, long delay) {
		if (!TransactionSynchronizationManager.isSynchronizationActive()) {
			LOGGER.info("Transaction synchronization is NOT ACTIVE. Executing right now, ONS Topic {}, Tag {} ", topic,
					tag);
			sendDelayMessage(topic, tag, message, delay);
			return;
		}

		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
			@Override
			public void afterCommit() {
				sendDelayMessage(topic, tag, message, delay);
			}
		});
	}

}
