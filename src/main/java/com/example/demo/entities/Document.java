package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@Table(name = "TB_DOCUMENTS")
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id")
    private ProcessEntity process;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_by_user_id")
    private UserEntity postedBy;

    private String url;

    private LocalDate postDate;

    public Document() {

    }
}
