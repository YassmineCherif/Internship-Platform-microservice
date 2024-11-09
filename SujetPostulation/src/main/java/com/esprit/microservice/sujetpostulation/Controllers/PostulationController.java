package com.esprit.microservice.sujetpostulation.Controllers;

import com.esprit.microservice.sujetpostulation.Entities.Postulation;
import com.esprit.microservice.sujetpostulation.Entities.Sujet;
import com.esprit.microservice.sujetpostulation.Repositories.PostulationRepository;
import com.esprit.microservice.sujetpostulation.Services.EmailService;
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
@RequestMapping("/postulation")
@CrossOrigin(origins = "http://localhost:8082")
@Slf4j
public class PostulationController {

    private final PostulationRepository postulationRepository;
    private final PostulationService postulationService;

    @Autowired
    private EmailService emailService;

    /*//milliseconds to days.
    //(1000 milliseconds * 60 seconds * 60 minutes * 24 hours).
    private boolean isValidPemailServiceeriod(Date dateDebut, Date dateFin, int minimumDurationInDays) {
        long differenceInTime = dateFin.getTime() - dateDebut.getTime();
        long differenceInDays = differenceInTime / (1000 * 3600 * 24);
        return differenceInDays >= minimumDurationInDays;
    }*/


    @PostMapping("/add")
    public Postulation addSujet(@RequestBody Postulation postulation ) {
        return postulationService.addPostulation(postulation);
    }


    @GetMapping("/byAccepted")
    public List<Postulation> filterByAccepted() {
        return postulationService.getPostulationsByStatus(1);
    }

    @GetMapping("/byRefused")
    public List<Postulation> filterByRefused() {
        return postulationService.getPostulationsByStatus(2);
    }

    @GetMapping("/byAttente")
    public List<Postulation> filterByAttente() {
        return postulationService.getPostulationsByStatus(0);
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


    @PutMapping("/confirm-postulation/{idP}/{toemail}")
    public void confirmPostulation(@PathVariable long idP, @PathVariable String toemail ) {


        Postulation postulation = postulationService.findById(idP);
        postulation.setStatus(1);
        postulationService.updatePostulation(postulation, idP);
            // Send confirmation email to the student
            sendResultEmail(postulation,toemail, "accepted");
            //Stage stage = new Stage();
            //stageService.addStage(stage);




        /*
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
            sendResultEmail(postulation,toemail, "accepted");
            //Stage stage = new Stage();
            //stageService.addStage(stage);
        }

        return postulation;
        */

    }


    @PutMapping("/reject-postulation/{idP}/{toemail}")
    public void rejectPostulation(@PathVariable long idP,@PathVariable String toemail ) {

            Postulation postulation = postulationService.findById(idP);
            postulation.setStatus(1);
            postulationService.updatePostulation(postulation, idP);
            // Send confirmation email to the student
            sendResultEmail(postulation,toemail, "rejected");




        /*log.info("idP: " + idP);
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
            sendResultEmail(postulation,toemail, "rejected");

        }

        return postulation;*/
    }


        private void sendResultEmail(Postulation postulation, String toemail, String result) {
            String subject = "Result of Your Internship Application";
            String body = "Hello " + ", your internship application for the company " + postulation.getSujet().getNomentreprise() +
                    " has been "+ result + " .";
            emailService.sendSimpleEmail(toemail, subject, body);
        }


}