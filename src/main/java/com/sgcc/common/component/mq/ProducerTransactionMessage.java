package com.sgcc.common.component.mq;
import javax.annotation.Generated;
public class ProducerTransactionMessage {
   
    private String messsageId;

   
    private String body;

    
    private String messageStatus;

 
    private String updateTime;

   
    private String createTime;

   
    private String sendTime;

  
    private String topic;

   
    private String tag;

    
	public ProducerTransactionMessage() {
		super();
	}


	@Generated("SparkTools")
	private ProducerTransactionMessage(Builder builder) {
		this.messsageId = builder.messsageId;
		this.body = builder.body;
		this.messageStatus = builder.messageStatus;
		this.updateTime = builder.updateTime;
		this.createTime = builder.createTime;
		this.sendTime = builder.sendTime;
		this.topic = builder.topic;
		this.tag = builder.tag;
	}

 
    public String getMesssageId() {
        return messsageId;
    }


	/**
	 * Creates builder to build {@link ProducerTransactionMessage}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}


	/**
	 * Builder to build {@link ProducerTransactionMessage}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String messsageId;
		private String body;
		private String messageStatus;
		private String updateTime;
		private String createTime;
		private String sendTime;
		private String topic;
		private String tag;

		private Builder() {
		}

		public Builder withMesssageId(String messsageId) {
			this.messsageId = messsageId;
			return this;
		}

		public Builder withBody(String body) {
			this.body = body;
			return this;
		}

		public Builder withMessageStatus(String messageStatus) {
			this.messageStatus = messageStatus;
			return this;
		}

		public Builder withUpdateTime(String updateTime) {
			this.updateTime = updateTime;
			return this;
		}

		public Builder withCreateTime(String createTime) {
			this.createTime = createTime;
			return this;
		}

		public Builder withSendTime(String sendTime) {
			this.sendTime = sendTime;
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

		public ProducerTransactionMessage build() {
			return new ProducerTransactionMessage(this);
		}
	}
   
 
}