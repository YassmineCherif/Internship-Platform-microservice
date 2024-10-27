package com.example.reclamationgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class ReclamationGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReclamationGateWayApplication.class, args);
    }


    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("Reclamation", r->r.path("/reclamation/**")
                .uri("http://localhost:8081"))

                .route("job-s", r->r.path("/jobs/**")
                .uri("http://localhost:8082"))
                .build();
    }
}
