package org.example.microservice.reclamation.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.microservice.reclamation.Entities.Enums.Statut_reclamation;
import org.example.microservice.reclamation.Entities.Enums.Type_reclamation;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long id_Reclamation;

    String description_Reclamation;

    @Enumerated(EnumType.STRING)
    Type_reclamation typeReclamation;

    String title;

    LocalDateTime date_Reclamation;

    @PrePersist
    public void prePersist() {
        this.date_Reclamation = LocalDateTime.now();
    }

    @Enumerated(EnumType.STRING)
    Statut_reclamation statutReclamation;

    @OneToOne ()
    Reponse reponse;
}
