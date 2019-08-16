package com.sgcc.common.component.mq;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.sgcc.common.component.mq.service.ConsumerTransactionMessageService;
import com.sgcc.common.spring.SpringContextUtils;
import com.sgcc.utils.TimeUtil;

/**
 * @version 1.0
 */
@Component
@EnableAsync
public class MQMessageListener implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger("aliyun-mq-logger");

	private final List<MQMessageHandler> messageHandlers;

	public MQMessageListener(List<MQMessageHandler> messageHandlers) {
		this.messageHandlers = messageHandlers;
	}
	

	@Override
	public Action consume(Message message, ConsumeContext context) {
		LOGGER.info("MQ|RECEIVE|{}|{}|{}|{}|{}", message.getKey(), message.getTopic(), message.getTag(),
				message.getMsgID(), messageHandlers);
		ConsumerTransactionMessage consumerTransactionMessage=null;
		try {
			Object messageBody;
			try (ObjectInputStream objectInputStream = new ObjectInputStream(
					new ByteArrayInputStream(message.getBody()))) {
				// noinspection unchecked
				messageBody = objectInputStream.readObject();
			} catch (IOException | ClassNotFoundException e) {
				LOGGER.debug("MQ|CAST2STRING|{}|{}|{}|{}", message.getKey(), message.getTopic(), message.getTag(),
						message.getMsgID());
				messageBody = new String(message.getBody());
				consumerTransactionMessage = ConsumerTransactionMessage.builder()
						.withMesssageId(message.getMsgID())
						.withTag(message.getTag())
						.withTopic(message.getTopic())
						.withMessageStatus(MQMessageStatus.CONSUMED_FAILED.toString())
						.withCreateTime(TimeUtil.getCurrentTime17ByMillis())
						.withMark("Read MQ InputStream Error|"+e.getMessage())
						.build();
			}
			for (MQMessageHandler messageHandler : messageHandlers) {
				String envTag = messageHandler.getTag();
				String topic = messageHandler.getTopic();
				if (envTag.equals(message.getTag()) && topic.equals(message.getTopic())) {
					LOGGER.info("MQ|Handler|{}|{}|{}", message.getTag(), messageHandler.getClass().getName(),
							messageBody);
					// 防止重复消费
					StringRedisTemplate redisTemplate = SpringContextUtils.getBean(StringRedisTemplate.class);
					if (!redisTemplate.opsForValue().setIfAbsent(message.getKey(), message.getKey())) {
						LOGGER.info("MQ|RECONSUME|{}|{}|{}|{}", message.getKey(), message.getTopic(), message.getTag(),
								message.getMsgID());
						continue;
					}
					try {
						messageHandler.handle(messageBody);
						consumerTransactionMessage = ConsumerTransactionMessage.builder()
									.withMesssageId(message.getMsgID())
									.withTag(envTag)
									.withTopic(topic)
									.withMessageStatus(MQMessageStatus.CONSUMED.toString())
									.withCreateTime(TimeUtil.getCurrentTime17ByMillis())
									.build();
					} catch (Exception e) { // 消息处理失败输出日志, 终止后续的消息处理,
											// 同时不需要MQ重新发送消息
						consumerTransactionMessage = ConsumerTransactionMessage.builder()
								.withMesssageId(message.getMsgID())
								.withTag(envTag)
								.withTopic(topic)
								.withMessageStatus(MQMessageStatus.CONSUMED_FAILED.toString())
								.withCreateTime(TimeUtil.getCurrentTime17ByMillis())
								.withMark("unknown errror"+e.getMessage())
								.build();
						LOGGER.error("MQ|Handler|ERROR|" + messageHandler.getClass() + "|" + messageBody, e);
						break;
					}
				}
			}
		} catch (Exception e) {
			consumerTransactionMessage=ConsumerTransactionMessage.builder()
					.withMesssageId(message.getMsgID())
					.withTag(message.getTag())
					.withTopic(message.getTopic())
					.withCreateTime(TimeUtil.getCurrentTime17ByMillis())
			.withMessageStatus(MQMessageStatus.CONSUMED_FAILED.toString()).build();
			LOGGER.error("MQ|FatalError|{}|{}|{}|{}", message.getKey(), message.getTopic(), message.getTag(),
					message.getMsgID(), e);
		}
		try {
			ConsumerTransactionMessageService consumerTransactionMessageService = SpringContextUtils.getBean(ConsumerTransactionMessageService.class);
			System.out.println(consumerTransactionMessage.getMesssageId()+"=================");
			consumerTransactionMessageService.save(consumerTransactionMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
		return Action.CommitMessage;
	}

}
