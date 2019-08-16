package com.sgcc.schedule.job.xxl;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.xxl.job.core.executor.XxlJobExecutor;

/**
 * xxl-job config
 *
 * @author xuxueli 2017-04-28
 */
//@Configuration
//@ComponentScan(basePackages = "com.sgcc.schedule.job.xxl.jobhandler")
public class XxlJobConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(XxlJobConfig.class);

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private String port;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private String logRetentionDays;

    @Value("${log.dir}")
    private String logDir;

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor() {
        LOGGER.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
        xxlJobExecutor.setAdminAddresses(adminAddresses);
        xxlJobExecutor.setAppName(appName);
        xxlJobExecutor.setIp(ip);
        if (StringUtils.isNotBlank(port)) {
            xxlJobExecutor.setPort(Integer.parseInt(port));
        }
        xxlJobExecutor.setAccessToken(accessToken);
        xxlJobExecutor.setLogPath(logPath);
        if (StringUtils.isNotBlank(logRetentionDays)) {
            xxlJobExecutor.setLogRetentionDays(Integer.parseInt(logRetentionDays));
        }

        return xxlJobExecutor;
    }

//    private String getLogPath() {
//        Path path = FileSystems.getDefault().getPath(logDir, logPath);
//        return path.toString();
//    }

}