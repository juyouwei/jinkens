package com.sgcc.common.component;
import java.io.Serializable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import com.alibaba.druid.pool.DruidDataSource;

@Component
@ConfigurationProperties(prefix="spring.datasource")
public class DataSourceDomain implements Serializable{

	private static final long serialVersionUID = -3308667328563696468L;
	
	private String driverClassName;
	
	private String username;
	
	private String password;
	
	private String url;
	
	private Integer maxActive;
	
	private Integer initialSize;
	
	private Integer maxWait;
	
	private Integer minIdle;
	
	
	private long timeBeteenEvictionRunsMillis;
	private long minEvictableIdelTimeMillis;
	private Boolean keepAlive;
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getMaxActive() {
		return maxActive;
	}
	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}
	public Integer getInitialSize() {
		return initialSize;
	}
	public void setInitialSize(Integer initialSize) {
		this.initialSize = initialSize;
	}
	public Integer getMaxWait() {
		return maxWait;
	}
	public void setMaxWait(Integer maxWait) {
		this.maxWait = maxWait;
	}
	public Integer getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}
	public long getTimeBeteenEvictionRunsMillis() {
		return timeBeteenEvictionRunsMillis;
	}
	public void setTimeBeteenEvictionRunsMillis(long timeBeteenEvictionRunsMillis) {
		this.timeBeteenEvictionRunsMillis = timeBeteenEvictionRunsMillis;
	}
	public long getMinEvictableIdelTimeMillis() {
		return minEvictableIdelTimeMillis;
	}
	public void setMinEvictableIdelTimeMillis(long minEvictableIdelTimeMillis) {
		this.minEvictableIdelTimeMillis = minEvictableIdelTimeMillis;
	}
	public Boolean getKeepAlive() {
		return keepAlive;
	}
	public void setKeepAlive(Boolean keepAlive) {
		this.keepAlive = keepAlive;
	}
	
	public void config(DruidDataSource dataSource){
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setMaxActive(maxActive);
		dataSource.setInitialSize(initialSize);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxWait(maxWait);
		dataSource.setTimeBetweenEvictionRunsMillis(timeBeteenEvictionRunsMillis);
		dataSource.setMinEvictableIdleTimeMillis(minEvictableIdelTimeMillis);
		dataSource.setKeepAlive(keepAlive);
	}
	
	
	

}
