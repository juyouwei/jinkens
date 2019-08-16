package common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.embedded.RedisServer;
import java.io.IOException;
import java.net.Socket;

@Configuration
@Profile({"ci"})
@EnableAutoConfiguration(exclude = RedisAutoConfiguration.class)
public class EmbeddedRedisConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedRedisConfig.class);

    private RedisServer redisServer;

    private static final String REDIS_SERVICE = "redisServiceBean";

    private final RedisProperties properties;

    public EmbeddedRedisConfig(RedisProperties properties) {
        this.properties = properties;
    }

    @Bean(name = REDIS_SERVICE, initMethod = "startRedis", destroyMethod = "stopRedis")
    public EmbeddedRedis embeddedRedis() {
        return new EmbeddedRedis();
    }

    @Bean
    @DependsOn(REDIS_SERVICE)
    public JedisConnectionFactory redisConnectionFactory() {
        JedisPoolConfig poolConfig = this.properties.getPool() != null
                ? jedisPoolConfig() : new JedisPoolConfig();
        return applyProperties(new JedisConnectionFactory(poolConfig));
    }

    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        RedisProperties.Pool props = this.properties.getPool();
        config.setMaxTotal(props.getMaxActive());
        config.setMaxIdle(props.getMaxIdle());
        config.setMinIdle(props.getMinIdle());
        config.setMaxWaitMillis(props.getMaxWait());
        return config;
    }

    protected final JedisConnectionFactory applyProperties(
            JedisConnectionFactory factory) {
        configureConnection(factory);
        factory.setDatabase(this.properties.getDatabase());
        if (this.properties.getTimeout() > 0) {
            factory.setTimeout(this.properties.getTimeout());
        }
        return factory;
    }

    private void configureConnection(JedisConnectionFactory factory) {
        factory.setHostName(this.properties.getHost());
        factory.setPort(this.properties.getPort());
        if (this.properties.getPassword() != null) {
            factory.setPassword(this.properties.getPassword());
        }
    }

    class EmbeddedRedis {
        private static final int PORT = 6377;

        public void startRedis() throws IOException {
            if (!isPortAvailable()) {
                return;
            }
            LOGGER.info("启动内嵌Redis...");
            try {
                redisServer = new RedisServer(PORT);
                redisServer.start();
            } catch (Exception e) {
                LOGGER.error("启动内嵌redis...失败, {}", e.getMessage(), e);
                System.exit(0);
            }
        }

        public void stopRedis() {
            if (isPortAvailable()) {
                return;
            }
            redisServer.stop();
            LOGGER.info("停止内嵌Redis...");
        }

        private boolean isPortAvailable() {
            try (Socket ignored = new Socket("localhost", PORT)) {
                return false;
            } catch (IOException ignored) {
                return true;
            }
        }
    }
}