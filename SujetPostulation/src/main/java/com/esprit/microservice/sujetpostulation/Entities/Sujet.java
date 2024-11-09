package com.esprit.microservice.sujetpostulation.Entities;

import  com. esprit. microservice. sujetpostulation. Entities.Typesujet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sujet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long idsujet;
    String titre;
    String description;
    String lieu;
    int duree;
    int nbretudiant ;
    String requirements;
    String nomentreprise;
    String mailentreprise;

    @Enumerated(EnumType.STRING)
    Typesujet typesujet;

    /*
     @JsonIgnore
    @OneToMany(mappedBy = "sujet")
    private Set<Postulation> postulationtSet;


    @ManyToOne
    @JoinColumn(name = "idadmin")
    private User user;
     */

    }