package common;
import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_17;

import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;

import junit.framework.Assert;

/**
 * @author redstarstar, star.hong@gmail.com
 * @version 1.0
 */
@Configuration
@Profile({"ci"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class EmbeddedMySQLConfigTest {

	private EmbeddedMysql embeddedMysql;

    @Test
    public void testStart(){
        MysqldConfig config = aMysqldConfig(v5_7_17)
                .withCharset(UTF8)
                .withPort(3356)
                .withUser("test", "test")
                .withTimeZone("Asia/Shanghai")
                .withServerVariable("lower_case_table_names", 1)
                .build();

        embeddedMysql = anEmbeddedMysql(config)
                .addSchema("test",
                        classPathScript("sql/001_ddl.sql"),
                        classPathScript("sql/002_origin_ddl.sql"),
                        classPathScript("sql/2018_05_18_user.sql")
                )
                .start();
        
       Assert.assertEquals(3356,embeddedMysql.getConfig().getPort());
    }
  

    
}
