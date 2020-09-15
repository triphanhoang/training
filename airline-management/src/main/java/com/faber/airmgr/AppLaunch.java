package com.faber.airmgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.faber.airmgr")
public class AppLaunch {
    public static void main(String[] args) {
        SpringApplication.run(AppLaunch.class, args);
    }
}
