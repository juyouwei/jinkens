package com.sgcc.common.component.mq;


import java.util.Date;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import com.aliyun.openservices.shade.com.alibaba.fastjson.serializer.SerializeConfig;
import com.aliyun.openservices.shade.com.alibaba.fastjson.serializer.SerializerFeature;
import com.aliyun.openservices.shade.com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

public class MQProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger("aliyun-mq-logger");

    private final OrderProducer producer;


    private static final SerializeConfig SERIALIZE_CONFIG = new SerializeConfig();

    static {
        SERIALIZE_CONFIG.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }

    public MQProducer(OrderProducer producer) {
        this.producer = producer;
    }

    public void sendOrderedMessage(String topic, String tag, MQMessage message, String shardingKey) {
        String json = JSON.toJSONString(message.getData(), SERIALIZE_CONFIG, SerializerFeature.WriteClassName);
        Message msg = new Message(topic, tag, StringUtils.getBytesUtf8(json));
        msg.setKey(message.getMessageKey());
        try {
            producer.send(msg, shardingKey);
        } catch (Exception e) {
            LOGGER.error("MQ|SEND|FAILED", e);
        }
    }
}
