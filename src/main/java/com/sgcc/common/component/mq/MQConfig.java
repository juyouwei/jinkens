package com.sgcc.common.component.mq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MQConfig {

    @Bean
    public SimpleMQProducer simpleMQProducer(MQProducerFactory producerFactory) {
        return producerFactory.createDefaultProducer();
    }

    @Bean
    public MQProducer mQProducer(MQOrderProducerFactory producerFactory) {
        return producerFactory.createDefaultProducer();
    }
}
