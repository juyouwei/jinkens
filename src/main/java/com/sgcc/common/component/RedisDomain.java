package com.sgcc.common.component;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix="spring.redis.cluster")
public class RedisDomain {
  
	private String nodes;
	
	private Integer maxRedirects;
	
	private Integer timeout;
	private String secretcode;
	
	public String getNodes() {
		return nodes;
	}
	public void setNodes(String nodes) {
		this.nodes = nodes;
	}
	public Integer getMaxRedirects() {
		return maxRedirects;
	}
	public void setMaxRedirects(Integer maxRedirects) {
		this.maxRedirects = maxRedirects;
	}
	
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	public String getSecretcode() {
		return secretcode;
	}
	public void setSecretcode(String secretcode) {
		this.secretcode = secretcode;
	}
	
}
