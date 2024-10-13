package com.esprit.microservice.sujetpostulation.Repositories;

import  com.esprit.microservice.sujetpostulation.Entities.Sujet;
import  com.esprit.microservice.sujetpostulation.Entities.Typesujet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SujetRepository extends JpaRepository<Sujet, Long> {

    List<Sujet> findByOrderByMailentrepriseAsc();

    List<Sujet> findByTypesujet(Typesujet typeSujet);

    List<Sujet> findByNomentrepriseContainingIgnoreCase(String searchTerm);

    List<Sujet> findByNomentrepriseContainingIgnoreCaseOrRequirementsContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String nomentrepriseSearchTerm, String requirementsSearchTerm, String descriptionSearchTerm);

    List<Sujet>findByOrderByNbretudiantDesc();

    List<Sujet>findByOrderByNbretudiantAsc();

    List<Sujet>findByOrderByDureeDesc();

    List<Sujet>findByOrderByDureeAsc();

  //  List<Sujet> findAllByUser(User user);
}