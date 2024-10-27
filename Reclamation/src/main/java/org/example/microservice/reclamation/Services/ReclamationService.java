package org.example.microservice.reclamation.Services;

import org.example.microservice.reclamation.Entities.Reclamation;
import org.example.microservice.reclamation.Repositories.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReclamationService {
    @Autowired
    private ReclamationRepository reclamationRepository;

    public Reclamation addReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }
    public Reclamation updateReclamation(Long id_Reclamation, Reclamation newReclamtion) {
        if (reclamationRepository.findById(id_Reclamation).isPresent()) {

            Reclamation existingReclamation = reclamationRepository.findById(id_Reclamation).get();
            existingReclamation.setDate_Reclamation(newReclamtion.getDate_Reclamation());
            existingReclamation.setDescription_Reclamation(newReclamtion.getDescription_Reclamation());
            existingReclamation.setStatutReclamation(newReclamtion.getStatutReclamation());
            existingReclamation.setTypeReclamation(newReclamtion.getTypeReclamation());
            existingReclamation.setTitle(newReclamtion.getTitle());
            existingReclamation.setReponse(newReclamtion.getReponse());

            return reclamationRepository.save(existingReclamation);
        } else
            return null;
    }
    public String deleteReclamation(Long id_Reclamation) {
        if (reclamationRepository.findById(id_Reclamation).isPresent()) {
            reclamationRepository.deleteById(id_Reclamation);
            return "Reclamation Supprimé !";
        } else
            return "Reclamation non supprimé !";
    }
}
