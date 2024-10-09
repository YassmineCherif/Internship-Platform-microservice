package com.example.conventiongateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
public class ConventionGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConventionGateWayApplication.class, args);
    }
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){

        return builder.routes()
                .route("Convention", r->r.path("/api/services/convention/**")
                        .uri("http://localhost:8080"))

                .route("job-s", r->r.path("/jobs/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}
