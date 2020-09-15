package com.faber.airmgr.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HikariDataSourceConfig {

    private final HikariProperties hikariProperties;

    public HikariDataSourceConfig(HikariProperties config) {
        this.hikariProperties = config;
    }

    @Bean
    public HikariDataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(hikariProperties.getUrl());
        config.setUsername(hikariProperties.getUsername());
        config.setPassword(hikariProperties.getPassword());
        config.setMaximumPoolSize(hikariProperties.getMaxIdle());
        config.setDriverClassName(hikariProperties.getDrive());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }
}
