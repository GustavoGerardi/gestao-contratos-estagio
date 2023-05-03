package com.example.demo.repositories;

import com.example.demo.entities.Document;
import com.example.demo.entities.ProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByProcess(ProcessEntity process);
}
