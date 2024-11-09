package com.example.msgestionjournal.Repositories;

import com.example.msgestionjournal.Entities.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepo extends JpaRepository <Journal, Long> {
}
