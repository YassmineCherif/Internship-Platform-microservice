package org.example.microservice.reclamation.Controller;

import org.example.microservice.reclamation.Entities.Reclamation;
import org.example.microservice.reclamation.Services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reclamations")
public class ReclamationRestAPI {
    private String title="Hello, i'm the reclamation Micro-Service";
    @RequestMapping("/reclamation")
    public String sayHello(){
        System.out.println(title);
        return title;
    }

    @Autowired
    private ReclamationService reclamationService;

    @PostMapping(consumes = MediaType.APPLICATION_ATOM_XML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Reclamation> createReclamationResponseEntity (@RequestBody Reclamation reclamation) {
        return new ResponseEntity<>(reclamationService.addReclamation(reclamation), HttpStatus.OK);
    }

    @PutMapping(value = "/{id_Reclamation}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Reclamation> updateReclamation(@PathVariable(value = "id_Reclamation") Long id_Reclamation, @RequestBody Reclamation reclamation) {
        return new ResponseEntity<>(reclamationService.updateReclamation(id_Reclamation, reclamation),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id_Reclamation}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteReclamation(@PathVariable(value = "id_Reclamation") Long id_Reclamation) {
        return new ResponseEntity<>(reclamationService.deleteReclamation(id_Reclamation), HttpStatus.OK);
    }
}
