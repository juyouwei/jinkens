package com.sgcc.common.component.mq;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * @version 1.0
 */
public class MQMessage {

    private String messageKey;
    private Serializable data;

    public MQMessage(String messageKey, Serializable data) {
        Validate.notBlank(messageKey, "Message Key is blank");
        Validate.notNull(data, "Message data is null");
        Validate.isTrue(StringUtils.contains(messageKey, "_"), "MessageKey must has A \"_\" between ID and TAG");
        this.messageKey = messageKey;
        this.data = data;
    }

    public MQMessage(String businessId, String businessName, Serializable data) {
        Validate.notBlank(businessId, "Message businessId is blank");
        Validate.notBlank(businessName, "Message businessName is blank");
        Validate.notNull(data, "Message data is null");
        this.messageKey = makeMessageKey(businessId, businessName);
        this.data = data;
    }

    private static String makeMessageKey(String businessId, String businessName) {
        return String.format("%s_%s", businessId, businessName);
    }

    public String getMessageKey() {
        return messageKey;
    }

    public Object getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof MQMessage)) return false;

        MQMessage that = (MQMessage) o;

        return new EqualsBuilder()
                .append(messageKey, that.messageKey)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(messageKey)
                .toHashCode();
    }
}
