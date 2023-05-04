package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "TB_PROCESS")
@AllArgsConstructor
public class ProcessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentUserEntity studentUserId;

    @OneToMany(mappedBy = "process")
    private final List<Document> documents = new ArrayList<>();

    private Long companyId;

    private Long documentType;

    private Long processStatus;

    private LocalDate localDate;

    public ProcessEntity() {

    }
}
