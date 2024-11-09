package com.example.msgestionjournal.Contollers;

import com.example.msgestionjournal.Entities.Journal;
import com.example.msgestionjournal.Services.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/services/journal")
@CrossOrigin(origins = "http://localhost:4200")
public class JournalController {

    public final JournalService journalService;


    @PostMapping
    public Journal addJournal(@RequestBody Journal journal) {
        return journalService.addJournal(journal);
    }

    @PutMapping
    public Journal updateJournal(@RequestBody Journal journal) {

        return journalService.updateJournal(journal);
    }

    @GetMapping("/{id_Journal}")
    public Journal findById(@PathVariable Long id_Journal) {

        // journalService.calculTotalNote(id_Journal);
        return journalService.findById(id_Journal);
    }

}
