package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private String password;

    private String email;
}

