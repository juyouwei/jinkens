package common;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.annotation.Resource;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import com.google.common.io.Files;
import com.sgcc.BreakPointApplication;
import ai.grakn.redismock.RedisServer;
@SpringBootTest(classes=BreakPointApplication.class)
@RunWith(SpringRunner.class)

public class EmbeddedRedisConfigTest {
	@Resource
    private StringRedisTemplate stringRedisTemplate;

    private static RedisServer redisServer = null;
    @BeforeClass
    public static void init() {
        try {
            redisServer = RedisServer.newRedisServer();
            redisServer.start();
            URL resource = EmbeddedRedisConfigTest.class.getClassLoader().getResource("application-ci.properties");
            Files.write("spring.redis.port=" + redisServer.getBindPort(), new File(resource.getFile()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public static void close() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }

    @Test
    public void testRedis() {
    	stringRedisTemplate.opsForValue().set("name", "tenmao");
    	String name = stringRedisTemplate.opsForValue().get("name");
        Assert.assertEquals("tenmao", name);
    }
}

