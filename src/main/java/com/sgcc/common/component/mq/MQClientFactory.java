package com.sgcc.common.component.mq;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.PropertyValueConst;
import com.sgcc.utils.PropertyUtil;

/**
 * Aliyun MQ客户端工厂
 * @version 1.0
 */
//@Service
public class MQClientFactory {

    @Value("${aliyun.access-key-id}")
    private String accessKeyId;
    @Value("${aliyun.access-key-secret}")
    private String accessKeySecret;
    @Value("${mq.endpoint}")
    private String endpoint;
    @Value("${mq.producer-id}")
    private String producerId;
    @Value("${mq.consumer-id}")
    private String consumerId;
    @Value("${message-queue.topics}")
    private String topics;
    @Value("${mq.max-reconsume-times}")
    private String maxReconsumeTimes;

    private final List<MQMessageHandler> messageHandlers;

    @Autowired
    public MQClientFactory(List<MQMessageHandler> messageHandlers) {
        this.messageHandlers = messageHandlers;
    }

    @PostConstruct
    public void init() {
        for (MQMessageHandler messageHandler : messageHandlers) {
       	    	AbstractMessageHandler handler = (AbstractMessageHandler) messageHandler;
       	        handler.setOnsClientFactory(this);
        }
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ConsumerId, consumerId);
        properties.put(PropertyKeyConst.AccessKey, accessKeyId);
        properties.put(PropertyKeyConst.SecretKey, accessKeySecret);
        properties.put(PropertyKeyConst.ONSAddr, endpoint);
        properties.put(PropertyKeyConst.MaxReconsumeTimes, maxReconsumeTimes);
        if (!PropertyUtil.isProduction()) {
            // test 测试环境，消息队列使用广播模式，所有consumer id 是 CID-zfzx-normal-test的节点，都会收到同一条消息，各节点会根据自己的分支名和TAG (TAG+GIT_BRANCH) 判断是否处理
            properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.BROADCASTING);
        }
        Consumer consumer = ONSFactory.createConsumer(properties);
        String[] topicList = topics.split(",");
        for (String topic : topicList) {
            List<MQMessageHandler> handlers = messageHandlers.stream().filter(h -> StringUtils.equals(h.getTopic(), topic)).collect(Collectors.toList());
            consumer.subscribe(topic, "*", new MQMessageListener(handlers));
        }
        consumer.start();
    }

}
