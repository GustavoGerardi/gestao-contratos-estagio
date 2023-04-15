package com.example.demo.entities;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Builder
@Getter
public class AccountEntity {
    @Id
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

    private String password;

    private String email;
}
