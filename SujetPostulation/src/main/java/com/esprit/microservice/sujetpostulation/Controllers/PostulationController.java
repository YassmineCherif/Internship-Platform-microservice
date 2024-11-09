package com.esprit.microservice.sujetpostulation.Controllers;

import com.esprit.microservice.sujetpostulation.Entities.Postulation;
import com.esprit.microservice.sujetpostulation.Repositories.PostulationRepository;
import com.esprit.microservice.sujetpostulation.Services.PostulationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date; // Import Date class
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/services/postulation")
@CrossOrigin(origins = "http://localhost:8082")
@Slf4j
public class PostulationController {

    private final PostulationRepository postulationRepository;
    private final PostulationService postulationService;


    //milliseconds to days.
    //(1000 milliseconds * 60 seconds * 60 minutes * 24 hours).
    private boolean isValidPeriod(Date dateDebut, Date dateFin, int minimumDurationInDays) {
        long differenceInTime = dateFin.getTime() - dateDebut.getTime();
        long differenceInDays = differenceInTime / (1000 * 3600 * 24);
        return differenceInDays >= minimumDurationInDays;
    }


    @GetMapping("/byAccepted")
    public List<Postulation> filterByAccepted() {
        List<Postulation> acceptedPostulations = postulationService.getPostulationsByStatus(1);
        return acceptedPostulations;
    }

    @GetMapping("/byRefused")
    public List<Postulation> filterByRefused() {
        List<Postulation> refusedPostulations = postulationService.getPostulationsByStatus(2);
        return refusedPostulations;
    }

    @GetMapping("/byAttente")
    public List<Postulation> filterByAttente() {
        List<Postulation> waitingPostulations = postulationService.getPostulationsByStatus(0);
        return waitingPostulations;
    }


    @CrossOrigin(origins = "http://localhost:8082")
    @PutMapping("/{idP}")
    public Postulation updatePostulation(@RequestBody Postulation postulation, @PathVariable long idP) {
        return postulationService.updatePostulation(postulation, idP);
    }


    @GetMapping
    public List<Postulation> findAll() {
        return postulationService.findAll();
    }


    @GetMapping("/{idP}")
    public Postulation getById(@PathVariable long idP) {
        return postulationService.findById(idP);
    }

    @DeleteMapping("/{idP}")
    public void delete(@PathVariable long idP) {
        postulationService.delete(idP);
    }


    @PutMapping("/confirm-postulation/{idP}/{userRole}")
    public Postulation confirmPostulation(@PathVariable long idP, @PathVariable String userRole) {
        log.info("idP: " + idP);
        log.info("idadmin: " + userRole);
        Postulation postulation = postulationService.findById(idP);
        if (userRole.equals("Agententreprise")) {
            postulation.setStatusentr(1);
            postulationService.updatePostulation(postulation, idP);
        } else if (userRole.equals("SuperAdmin") || userRole.equals("Agentesprit")) {
            postulation.setStatus(1);
            postulationService.updatePostulation(postulation, idP);
        }

        if (postulation.getStatusentr() == 1 && postulation.getStatus() == 1) {
            postulationService.updatePostulation(postulation, idP);
            // Send confirmation email to the student
           // sendConfirmationEmail(postulation);
            //Stage stage = new Stage();
            //stageService.addStage(stage);
        }

        return postulation;
    }


    @PutMapping("/reject-postulation/{idP}/{userRole}")
    public Postulation rejectPostulation(@PathVariable long idP, @PathVariable String userRole) {

        log.info("idP: " + idP);
        log.info("idadmin: " + userRole);
        Postulation postulation = postulationService.findById(idP);
        if (userRole.equals("Agententreprise")) {
            postulation.setStatusentr(2);
            postulationService.updatePostulation(postulation, idP);
        } else if (userRole.equals("SuperAdmin") || userRole.equals("Agentesprit")) {
            postulation.setStatus(2);
            postulationService.updatePostulation(postulation, idP);
        }

        if (postulation.getStatusentr() == 2 && postulation.getStatus() == 2) {
            postulationService.updatePostulation(postulation, idP);
            // Send confirmation email to the student
           // sendRejectionEmail(postulation);

        }

        return postulation;
    }
/**
    private void sendConfirmationEmail(Postulation postulation) {
        String toEmail = postulation.getUser().getEmail();
        String subject = "Confirmation of your internship application";
        String body = "Hello " + postulation.getUser().getFirstName() + " " + postulation.getUser().getLastName() +
                ", your internship application for the company " + postulation.getSujet().getNomentreprise() +
                " has been accepted.";
        emailService.sendSimpleEmail(toEmail, subject, body);
    }

    private void sendRejectionEmail(Postulation postulation) {
        String toEmail = postulation.getUser().getEmail();
        String subject = "Rejection of your internship application";
        String body = "Hello " + postulation.getUser().getFirstName() + " " + postulation.getUser().getLastName() +
                ", your internship application for the company " + postulation.getSujet().getNomentreprise() +
                " has been rejected.";
        emailService.sendSimpleEmail(toEmail, subject, body);
    }

*/
}