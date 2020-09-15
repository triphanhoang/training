package com.faber.airmgr.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource(value = "classpath:config/hikari.properties")
public class HikariProperties {

    @Value("${hikari.url}")
    private String url;

    @Value("${hikari.username}")
    private String username;

    @Value("${hikari.password}")
    private String password;

    @Value("${hikari.drive-class}")
    private String drive;

    @Value("${hikari.idle-timeout}")
    private Long timeout;

    @Value("${hikari.max-active}")
    private int maxActive;

    @Value("${hikari.max-idle}")
    private int maxIdle;
}
