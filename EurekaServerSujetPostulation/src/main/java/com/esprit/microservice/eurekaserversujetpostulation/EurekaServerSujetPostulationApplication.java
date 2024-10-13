package com.esprit.microservice.eurekaserversujetpostulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerSujetPostulationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerSujetPostulationApplication.class, args);
    }

}
