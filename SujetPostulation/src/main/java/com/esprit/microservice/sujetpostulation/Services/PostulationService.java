package com.esprit.microservice.sujetpostulation.Services;

import com.esprit.microservice.sujetpostulation.Entities.Postulation;
import com.esprit.microservice.sujetpostulation.Repositories.PostulationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostulationService {

    @Autowired
    private PostulationRepository postulationRepository;

    /*
    @Autowired
    private SujetRepository sujetRepository;
    */


    @Autowired
    private SujetService sujetservice ;

    public Postulation addPostulation(Postulation postulation) {
       postulation.setStatus(0);
       postulation.setStatusentr(0);
        return postulationRepository.save(postulation);
    }

    public List<Postulation> getPostulationsByStatus(int status) {

        return postulationRepository.findByStatus(status);
    }

    /*
    public List<Postulation> getPostulationsByStatusAndUserId(int status, Long userId) {
        return postulationRepository.findByUserIdAndStatus(status, userId);
    }
*/

    public Postulation updatePostulation(Postulation updatedPostulation, long idP) {
        // Retrieve the existing Postulation from the repository using the provided ID
        Postulation existingP = postulationRepository.findById(idP)
                .orElseThrow(() -> new EntityNotFoundException("Postulation not found"));

        // Update only the non-null fields from the updated Postulation
        if (updatedPostulation.getTitrecandidature() != null) {
            existingP.setTitrecandidature(updatedPostulation.getTitrecandidature());
        }
        if (updatedPostulation.getRegion() != null) {
            existingP.setRegion(updatedPostulation.getRegion());
        }
        if (updatedPostulation.getDatedeb() != null) {
            existingP.setDatedeb(updatedPostulation.getDatedeb());
        }
        if (updatedPostulation.getDatefin() != null) {
            existingP.setDatefin(updatedPostulation.getDatefin());
        }
        if (updatedPostulation.getLettremotivation() != null) {
            existingP.setLettremotivation(updatedPostulation.getLettremotivation());
        }
        if (updatedPostulation.getComm() != null) {
            existingP.setComm(updatedPostulation.getComm());
        }

        // Save the updated postulation
        return postulationRepository.save(existingP);
    }


    public List<Postulation> findAll() {
        return (List<Postulation>)postulationRepository.findAll();
    }

    public Postulation findById(long idP) {
        return postulationRepository.findById(idP)
                .orElseThrow(() -> new EntityNotFoundException("Postulation with id " + idP + " not found."));
    }




    public void delete(long idP) {
        // Check if the idP is > 0)
        if (idP <= 0) {
            throw new IllegalArgumentException("Invalid idP. Please provide a positive non-zero value.");
        }

        // Check if idP exists in the DB
        if (!postulationRepository.existsById(idP)) {
            throw new EntityNotFoundException("Postulation with id " + idP + " not found.");
        }

        // Delete the Postulation
        postulationRepository.deleteById(idP);
    }

    /*
    public String getSujetTypeById(long sujetId) {
        Sujet sujet = sujetRepository.findById(sujetId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + sujetId));
        return sujet.getTypesujet().toString();
    }

    public List<Postulation> getPostulationsBySujetIdAndAttente(Long sujetId) {
        return postulationRepository.findBySujetIdAndStatus(sujetId, 0); // Assuming "0" represents "attente" status
    }
*/

}
