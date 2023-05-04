package com.example.demo.repositories;

import com.example.demo.entities.Document;
import com.example.demo.entities.ProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByProcess(ProcessEntity process);

    @Query(value = "SELECT * FROM tb_documents WHERE process_id = :processId AND posted_by_user_id = :userId", nativeQuery = true)
    Document findByProcessIdAndUserId(@Param("processId") Long processId, @Param("userId") Long userId);
}
