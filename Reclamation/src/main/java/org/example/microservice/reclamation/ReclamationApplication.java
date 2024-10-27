package org.example.microservice.reclamation;

import org.example.microservice.reclamation.Entities.Enums.Statut_reclamation;
import org.example.microservice.reclamation.Entities.Enums.Type_reclamation;
import org.example.microservice.reclamation.Entities.Reclamation;
import org.example.microservice.reclamation.Repositories.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReclamationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReclamationApplication.class, args);
    }

    @Autowired
    private ReclamationRepository repository;

    @Bean
    ApplicationRunner init () {
        return (args) -> {

            repository.save(new Reclamation(null,"test",Type_reclamation.AUTRES,"title",null,Statut_reclamation.ENATTENTE,null));
            repository.save(new Reclamation(null,"test1",Type_reclamation.AUTRES,"title1",null,Statut_reclamation.ENATTENTE,null));
            repository.save(new Reclamation(null,"test2",Type_reclamation.AUTRES,"title2",null,Statut_reclamation.ENATTENTE,null));

            repository.findAll().forEach(System.out::println);
        };
    }

}
