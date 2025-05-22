package com.example.memo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = RedisRepositoriesAutoConfiguration.class)
@EnableTransactionManagement
@ComponentScan(basePackages = "com.example.memo")
public class VideoXApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoXApplication.class, args);
    }

}
