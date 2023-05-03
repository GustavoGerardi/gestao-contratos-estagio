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
    private final ProcessEntity process;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_by_user_id")
    private final UserEntity postedBy;
    
    private final String url;

    private final LocalDate postDate;
}
