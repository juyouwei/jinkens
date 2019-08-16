package com.sgcc.common.component.mq;



import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.order.OrderProducer;

//@Service
public class MQOrderProducerFactory {

    @Value("${aliyun.access-key-id}")
    private String accessKeyId;
    @Value("${aliyun.access-key-secret}")
    private String accessKeySecret;
    @Value("${mq.endpoint}")
    private String endpoint;
    @Value("${mq.producer-id}")
    private String producerId;

    private OrderProducer producer;

    @PostConstruct
    public void init() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ProducerId, producerId);
        properties.put(PropertyKeyConst.AccessKey, accessKeyId);
        properties.put(PropertyKeyConst.SecretKey, accessKeySecret);
        properties.put(PropertyKeyConst.ONSAddr, endpoint);
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");
        producer = ONSFactory.createOrderProducer(properties);
        producer.start();

    }

    @PreDestroy
    public void destroy() {
        producer.shutdown();
    }

    public MQProducer createDefaultProducer() {
        return new MQProducer(producer);
    }
}
