package com.example.demo.entities;

import com.example.demo.enums.DocumentType;
import com.example.demo.enums.ProcessStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "process")
@Getter
@Builder
public class Process {
    @Id
    private final UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private final StudentUserEntity studentUserId;

    @OneToMany(mappedBy = "process")
    private final List<Document> documents = new ArrayList<>();

    private final Long companyId;

    private final DocumentType documentType;

    private final ProcessStatus processStatus;

    private final LocalDate localDate;
}
