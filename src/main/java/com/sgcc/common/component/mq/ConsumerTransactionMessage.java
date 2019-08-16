package com.sgcc.common.component.mq;
import java.io.Serializable;

import javax.annotation.Generated;
public class ConsumerTransactionMessage implements Serializable{


	private static final long serialVersionUID = -8225187760011371576L;


	private final String messageId;
  
    
    private String messageStatus;

   
    private String createTime;

  
    private final String topic;

   
    private final String  tag;

    private String mark;
	public String getMesssageId() {
		return messageId;
	}


	public String getMessageStatus() {
		return messageStatus;
	}


	public String getCreateTime() {
		return createTime;
	}


	public String getTopic() {
		return topic;
	}


	public String getTag() {
		return tag;
	}


	public String getMark() {
		return mark;
	}


	@Generated("SparkTools")
	private ConsumerTransactionMessage(Builder builder) {
		this.messageId = builder.messageId;
		this.messageStatus = builder.messageStatus;
		this.createTime = builder.createTime;
		this.topic = builder.topic;
		this.tag = builder.tag;
		this.mark = builder.mark;
		
	}


	/**
	 * Creates builder to build {@link ConsumerTransactionMessage}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}


	/**
	 * Builder to build {@link ConsumerTransactionMessage}.
	 */
	@Generated("SparkTools")
	public static class Builder {
		private String messageId;
		private String messageStatus;
		private String createTime;
		private String topic;
		private String tag;
		private String mark;
		private Builder() {
		}

		public Builder withMesssageId(String messageId) {
			this.messageId = messageId;
			return this;
		}

		public Builder withMessageStatus(String messageStatus) {
			this.messageStatus = messageStatus;
			return this;
		}

		public Builder withCreateTime(String createTime) {
			this.createTime = createTime;
			return this;
		}

		public Builder withTopic(String topic) {
			this.topic = topic;
			return this;
		}

		public Builder withTag(String tag) {
			this.tag = tag;
			return this;
		}
		public Builder withMark(String mark) {
			this.mark = mark;
			return this;
		}
		public ConsumerTransactionMessage build() {
			return new ConsumerTransactionMessage(this);
		}
	}
    
}
