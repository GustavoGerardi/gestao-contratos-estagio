package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@Table(name = "TB_DOCUMENTS")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id")
    private final Process process;

    private final String name;

    private final String url;

    private final LocalDate postDate;
}
