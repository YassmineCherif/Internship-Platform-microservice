package com.esprit.microservice.sujetpostulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SujetPostulationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SujetPostulationApplication.class, args);
    }

}
