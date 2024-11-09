package com.example.msgestionjournal.ServiceImp;

import com.example.msgestionjournal.Entities.Journal;
import com.example.msgestionjournal.Repositories.JournalRepo;
import com.example.msgestionjournal.Services.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class JournalServiceImp implements JournalService {

    private final JournalRepo journalRepos;

    @Override
    public Journal addJournal(Journal journal) {

        return journalRepos.save(journal);
    }

    @Override
    public Journal updateJournal(Journal journal) {
        return journalRepos.save(journal);


    }
    @Override
    public Set<Journal> findAll() {
        return (Set<Journal>) journalRepos.findAll();
    }

    @Override
    public Journal findById(Long id_Journal) {
        return journalRepos.findById(id_Journal).orElse(null) ;

    }
}
