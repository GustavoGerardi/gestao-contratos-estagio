package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "TB_PROCESS")
public class Process {
    @Id
    private final Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private final StudentUserEntity studentUserId;

    @OneToMany(mappedBy = "process")
    private final List<Document> documents = new ArrayList<>();

    private final Long companyId;
    
    private final Long documentType;

    private final Long processStatus;

    private final LocalDate localDate;
}
