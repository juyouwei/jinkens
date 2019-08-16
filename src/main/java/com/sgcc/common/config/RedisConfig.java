package com.sgcc.common.config;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import com.sgcc.BreakPointApplication;
import com.sgcc.common.component.RedisDomain;
import redis.clients.jedis.JedisPoolConfig;
import sun.misc.BASE64Decoder;
@Configuration
public class RedisConfig {

	@Autowired
	private RedisDomain redisDomain;
	
	  /**
     * 配置 Redis 连接池信息
     */
    @Bean
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig =new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis(-1L);
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setMaxTotal(Integer.MIN_VALUE);
        jedisPoolConfig.setTestOnBorrow(true);
        return jedisPoolConfig;
    }

	@Bean
	public RedisClusterConfiguration  jedisCluster() throws IOException{
	    InputStream inputStream = BreakPointApplication.class.getClassLoader().getResourceAsStream("application.properties");
	    Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					
				}
			}
		}
	  BASE64Decoder decoder = new BASE64Decoder();
		try {
			
			if (!StringUtils.isBlank(properties.getProperty("jasypt.encryptor.secretcode"))) {
				properties.put(new String(decoder.decodeBuffer("amFzeXB0LmVuY3J5cHRvci5wYXNzd29yZA==")),
						properties.getProperty("jasypt.encryptor.secretcode"));
			}
//			if (!StringUtils.isBlank(properties.getProperty("spring.datasource.secretcode"))) {
//				properties.put(new String(decoder.decodeBuffer("c3ByaW5nLmRhdGFzb3VyY2UucGFzc3dvcmQ=")),
//						properties.getProperty("spring.datasource.secretcode"));
//			}
			if (!StringUtils.isBlank(properties.getProperty("spring.redis.secretcode"))) {
				properties.put(new String(decoder.decodeBuffer("c3ByaW5nLnJlZGlzLnBhc3N3b3Jk")),
						properties.getProperty("spring.redis.secretcode"));
			}
		} catch (IOException e) {
		} finally {
			decoder = null;
		}

		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
		String[] nodesArr = redisDomain.getNodes().split(",");
		Set<RedisNode> nodeSet=new HashSet<>();
		for (String hostAndPort : nodesArr) {
			String[] portArr = hostAndPort.split(":");
			nodeSet.add(new RedisNode(portArr[0].trim(), Integer.parseInt(portArr[1].trim())));
		}
		
		redisClusterConfiguration.setClusterNodes(nodeSet);
		redisClusterConfiguration.setMaxRedirects(redisDomain.getMaxRedirects());
		return redisClusterConfiguration;
		
	} 
	 /**
     * 配置 Redis 连接工厂
     */
    @Bean
    public JedisConnectionFactory getJedisConnectionFactory(RedisClusterConfiguration redisClusterConfiguration, JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
        jedisConnectionFactory.setPassword(redisDomain.getSecretcode());
        jedisConnectionFactory.setTimeout(redisDomain.getTimeout());
        return jedisConnectionFactory;
    }
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
		    RedisTemplate<String, String> redisTemplateForString = new RedisTemplate<String, String>();
	        redisTemplateForString.setConnectionFactory(factory);
	        Jackson2JsonRedisSerializer<String> serializer = new Jackson2JsonRedisSerializer<String>(String.class);
	        redisTemplateForString.setHashKeySerializer(serializer);
	        redisTemplateForString.setHashValueSerializer(serializer);
	        redisTemplateForString.setKeySerializer(serializer);
	        redisTemplateForString.setValueSerializer(serializer);
	        return redisTemplateForString;
	}
	 @Bean
	 public RedisTemplate<String, Long> redisTemplateForLong(RedisConnectionFactory factory) {
	        RedisTemplate<String, Long> redisTemplateForLong = new RedisTemplate<String, Long>();
	        redisTemplateForLong.setConnectionFactory(factory);
	        Jackson2JsonRedisSerializer<Long> serializer = new Jackson2JsonRedisSerializer<Long>(Long.class);
	        redisTemplateForLong.setHashKeySerializer(serializer);
	        redisTemplateForLong.setHashValueSerializer(serializer);
	        redisTemplateForLong.setKeySerializer(serializer);
	        redisTemplateForLong.setValueSerializer(serializer);
	        redisTemplateForLong.afterPropertiesSet();
	        return redisTemplateForLong;
	    }
}
