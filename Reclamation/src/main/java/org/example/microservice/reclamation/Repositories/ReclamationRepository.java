package org.example.microservice.reclamation.Repositories;

import org.example.microservice.reclamation.Entities.Reclamation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
    @Query("select r from Reclamation r where r.title like :title")
    public Page<Reclamation> reclamationByTitle(@Param("title") String t, Pageable pageable);
}
