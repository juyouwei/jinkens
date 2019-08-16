package com.sgcc.common.component.mq;



import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;

/**
 * @version 1.0
 */
//@Service
public class MQProducerFactory {
    @Value("${aliyun.access-key-id}")
    private String accessKeyId;
    @Value("${aliyun.access-key-secret}")
    private String accessKeySecret;
    @Value("${mq.endpoint}")
    private String endpoint;
    @Value("${mq.producer-id}")
    private String producerId;

    private Producer producer;

    @PostConstruct
    public void init() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ProducerId, producerId);
        properties.put(PropertyKeyConst.AccessKey, accessKeyId);
        properties.put(PropertyKeyConst.SecretKey, accessKeySecret);
        properties.put(PropertyKeyConst.ONSAddr, endpoint);
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");
        producer = ONSFactory.createProducer(properties);
        producer.start();

    }

    @PreDestroy
    public void destroy() {
        producer.shutdown();
    }

    public SimpleMQProducer createDefaultProducer() {
        return new SimpleMQProducer(producer);
    }

}
