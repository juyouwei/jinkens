package com.sgcc.common.component.mq;
public interface MQMessageHandler {

    void handle(Object messageBody);

    String getTopic();

    String getTag();
}
