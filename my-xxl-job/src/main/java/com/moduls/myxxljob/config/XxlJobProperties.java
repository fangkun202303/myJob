package com.moduls.myxxljob.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description: xxl-job的properties
 * @author: fangkun
 * @date: Created in 2019/7/15 11:29
 * @version:
 * @modified By:
 */
@Configuration
@ConfigurationProperties(prefix = "xxl.job.executor")
public class XxlJobProperties {

    private String ip;

    private String port;

    private String appName;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public String toString() {
        return "XxlJobProperties{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", appName='" + appName + '\'' +
                '}';
    }
}
