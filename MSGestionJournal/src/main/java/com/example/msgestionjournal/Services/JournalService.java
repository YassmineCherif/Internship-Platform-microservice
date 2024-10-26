package com.example.msgestionjournal.Services;

import com.example.msgestionjournal.Entities.Journal;

import java.util.Set;

public interface JournalService {

    Journal addJournal(Journal journal);

    Journal updateJournal(Journal journal);

    Set<Journal> findAll();

    Journal findById(Long id_Journal);
}