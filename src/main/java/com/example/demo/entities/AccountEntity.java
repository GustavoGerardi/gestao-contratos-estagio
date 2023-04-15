package com.example.demo.entities;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Id;
import java.util.UUID;

@Builder
@Getter
public class AccountEntity {
    @Id
    private UUID id;
    private UUID userId;
    private String password;
    private String email;
}
