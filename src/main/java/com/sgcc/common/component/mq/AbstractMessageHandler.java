package com.sgcc.common.component.mq;
public abstract class AbstractMessageHandler implements MQMessageHandler {

    private MQClientFactory onsClientFactory;

    protected MQClientFactory getOnsClientFactory() {
        return onsClientFactory;
    }

    public void setOnsClientFactory(MQClientFactory onsClientFactory) {
        this.onsClientFactory = onsClientFactory;
    }
}