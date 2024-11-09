package com.esprit.microservice.sujetpostulation.Controllers;

import com.esprit.microservice.sujetpostulation.Entities.*;
import com.esprit.microservice.sujetpostulation.Services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sujet")
@CrossOrigin(origins = "http://localhost:8082")
public class SujetController {



    private final SujetService sujetService;
  //  private final UserService userService;


    @PostMapping("/add")
    public Sujet addSujet(@RequestBody Sujet sujet ) {
        return sujetService.addSujet(sujet);
    }


    @CrossOrigin(origins = "http://localhost:8082")
    @PutMapping("/{idSujet}")
    public Sujet updateSujet(@RequestBody Sujet sujet, @PathVariable long idSujet) {
        return sujetService.updateSujet(sujet, idSujet);
    }

/*
    @GetMapping("/affich/{idadmin}")
    public List<Sujet> findAll(@PathVariable Long idadmin) {
        User user = userService.findById(idadmin);

        if (user != null) {
            if (user.getRole() == UserRole.SuperAdmin || user.getRole() == UserRole.Agentesprit) {
                return sujetService.findAllSortedByMailentreprisee();
            } else if (user.getRole() == UserRole.Agententreprise) {
                return sujetService.findAllByUser(user);
            }
        }

        return new ArrayList<>();
    }
*/


    @GetMapping("/All")
    public List<Sujet> findAll() {

        return sujetService.findAll();
    }




    @GetMapping("/search")
    public List<Sujet> searchSujets(@RequestParam String searchTerm) {
        return sujetService.searchSujets(searchTerm);
    }



    @GetMapping("/{idSujet}")
    public Sujet getById(@PathVariable long idSujet){
        return sujetService.findById(idSujet);
    }

    @DeleteMapping("/{idSujet}")
    public void delete(@PathVariable long idSujet) {
        sujetService.delete(idSujet);
    }


    @GetMapping("/filter/byNbretudiantDesc")
    public List<Sujet> filterByNbretudiantDescending() {
        return sujetService.findByNbretudiantOrderByNbretudiantDesc();
    }
    @GetMapping("/filter/byNbreEtudiantAsc")
    public List<Sujet> filterByNbretudiantAscending() {
        return sujetService.findByNbretudiantOrderByNbretudiantAsc();
    }

    @GetMapping("/filter/byDureeDesc")
    public List<Sujet> filterByDureeDescending() {
        return sujetService.findByDureeOrderByDureeDesc();
    }

    @GetMapping("/filter/byDureeAsc")
    public List<Sujet> filterByDureeAscending() {
        return sujetService.findByDureeOrderByDureeAsc();
    }


}