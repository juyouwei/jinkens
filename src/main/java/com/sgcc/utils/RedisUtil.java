package com.sgcc.utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.DefaultScriptExecutor;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@Component
public class RedisUtil implements InitializingBean{
	private static final String LOCK_SUCCESS = "OK";
	private static final String SET_IF_NOT_EXIST = "NX";
	private static final String SET_WITH_EXPIRE_TIME = "PX";
	private static final Long RELEASE_SUCCESS = 1L;

	private final String[] redisGroupName = new String[] { "default" };
	private final int DEFAULT_TIMEOUT = 6000;
	private final int DEFAULT_MAXREDIRECTS = 5;
	public Map<String, JedisCluster> jedisMap = new HashMap<>();
	public JedisCluster jedis;
	private final String defaultGroupName = "default";
	@Value("${spring.redis.cluster.nodes:}")
	private String nodes;
	@Value("${spring.redis.cluster.max-redirects:}")
	private String maxRedirects;
	@Value("${spring.redis.cluster.timeout:}")
	private String timeout;
	@Value("${spring.redis.secretcode:}")
	private String password;
    private static RedisTemplate redisTemplate;

    private static RedisScript<String> redisScript;

    private static DefaultScriptExecutor<String> scriptExecutor;

    private RedisUtil(StringRedisTemplate template) throws IOException {
        RedisUtil.redisTemplate = template;
        // 初始化lua脚本调用 的redisScript 和 scriptExecutor
        ClassPathResource luaResource = new ClassPathResource("get_next_seq.lua");
        EncodedResource encRes = new EncodedResource(luaResource, "UTF-8");
        String luaString = FileCopyUtils.copyToString(encRes.getReader());
        redisScript = new DefaultRedisScript<>(luaString, String.class);
        
    }

    @SuppressWarnings("unchecked")
	public static String getBusiId(String type) {
    	List<String> keyList = new ArrayList<>();
        keyList.add("{key}."+9);
        keyList.add("{key}.1");
        keyList.add("{key}."+TimeUtil.getFormatedCurrentDate12_()+new Random().nextInt(99)+" ");
        List<String> keyList2 = new ArrayList<>();
        String result = (String)redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                Object nativeConnection = connection.getNativeConnection();
                // 集群模式和单点模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                // 集群
                if (nativeConnection instanceof JedisCluster) {
                	String seq= (String) ((JedisCluster) nativeConnection).eval(redisScript.getScriptAsString(), keyList, keyList2);
                    return type+seq;
                }
 
                // 单点
                else if (nativeConnection instanceof Jedis) {
                	String seq= (String) ((Jedis) nativeConnection).eval(redisScript.getScriptAsString(), keyList, keyList2);
                	 return type+seq;
                }
                return null;
            }
        });
        return result;

    }

	@SuppressWarnings("unchecked")
	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			String[] e = this.nodes.split(",");
			GenericObjectPoolConfig config = new GenericObjectPoolConfig();
			config.setMaxIdle(20);
			config.setMaxTotal(Integer.MAX_VALUE);
			config.setMaxWaitMillis(-1L);
			config.setTestOnCreate(true);
			config.setTestOnBorrow(true);
			config.setTestOnReturn(true);
			config.setTestWhileIdle(true);
			HashSet jedisClusterNodes = new HashSet();

			int t_timeout;
			for (t_timeout = 0; t_timeout < e.length; ++t_timeout) {
				String[] t_maxRedirects = e[t_timeout].split(":");
				jedisClusterNodes.add(new HostAndPort(t_maxRedirects[0], Integer.parseInt(t_maxRedirects[1])));
			}

			t_timeout = StringUtils.isEmpty(this.timeout) ? 6000 : Integer.parseInt(this.timeout);
			int arg6 = StringUtils.isEmpty(this.maxRedirects) ? 5 : Integer.parseInt(this.maxRedirects);
			if (!StringUtils.isEmpty(this.password)) {
				this.jedisMap.put(this.redisGroupName[0],
						new JedisCluster(jedisClusterNodes, t_timeout, t_timeout, arg6, this.password, config));
			} else {
				this.jedisMap.put(this.redisGroupName[0],
						new JedisCluster(jedisClusterNodes, t_timeout, t_timeout, arg6, config));
			}

			this.jedis = (JedisCluster) this.jedisMap.get("default");
		} catch (Exception arg5) {
			arg5.printStackTrace();
		}

	}

		
	}


