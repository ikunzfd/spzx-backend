package com.zfd.spzx.manager;

import com.zfd.spzx.common.log.annotation.EnableAspect;
import com.zfd.spzx.manager.properties.MinioProperties;
import com.zfd.spzx.manager.properties.UserProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.zfd.spzx")
@EnableConfigurationProperties({UserProperties.class, MinioProperties.class})
@EnableScheduling
@EnableAspect
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }
}
