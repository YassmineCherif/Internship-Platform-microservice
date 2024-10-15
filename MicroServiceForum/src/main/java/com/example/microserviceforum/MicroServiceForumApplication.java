package com.example.microserviceforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
public class MicroServiceForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceForumApplication.class, args);
    }

}
