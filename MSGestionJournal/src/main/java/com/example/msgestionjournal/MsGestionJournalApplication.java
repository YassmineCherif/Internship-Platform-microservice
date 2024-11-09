package com.example.msgestionjournal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsGestionJournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsGestionJournalApplication.class, args);
    }

}
