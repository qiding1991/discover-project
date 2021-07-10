package com.kankan.discover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DiscoverProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoverProjectApplication.class, args);
    }

}
