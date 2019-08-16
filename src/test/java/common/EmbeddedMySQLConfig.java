package common;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.Socket;
import java.sql.Driver;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_17;

/**
 * @author redstarstar, star.hong@gmail.com
 * @version 1.0
 */
@Configuration
@Profile({"ci"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class EmbeddedMySQLConfig {

    private EmbeddedMysql embeddedMysql;

    private static final String DB_SERVICE = "dbServiceBean";

    @Bean(name = DB_SERVICE, initMethod = "startMySQL", destroyMethod = "stopMySQL")
    public EmbeddedMySQL embeddedMySQL() {
        return new EmbeddedMySQL();
    }

    @Bean
    @DependsOn(DB_SERVICE)
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(getDriverClassByName(dataSourceProperties.determineDriverClassName()));
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        return dataSource;
    }

    @SuppressWarnings("unchecked")
    private Class<Driver> getDriverClassByName(String className) {
        try {
            return (Class<Driver>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    class EmbeddedMySQL {

        private static final int PORT = 3356;

        public void startMySQL() {
            if (!isPortAvailable()) {
                return;
            }
            System.out.println("启动内嵌MySQL...");
            try {
                MysqldConfig config = aMysqldConfig(v5_7_17)
                        .withCharset(UTF8)
                        .withPort(PORT)
                        .withUser("leo", "leo")
                        .withTimeZone("Asia/Shanghai")
                        .withServerVariable("lower_case_table_names", 1)
                        .build();

                embeddedMysql = anEmbeddedMysql(config)
                        .addSchema("leo",
                                classPathScript("db/001_ddl.sql"),
                                classPathScript("db/002_origin_ddl.sql"),
                                classPathScript("db/2018_05_18_user.sql")
                        )
                        .start();
            } catch (Exception e) {
                System.err.println("启动内嵌MySQL...失败");
                e.printStackTrace();
                System.exit(0);
            }

        }

        public void stopMySQL() {
            if (isPortAvailable()) {
                return;
            }
            System.out.println("停止内嵌MySQL...");
            embeddedMysql.stop();
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
