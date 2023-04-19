package com.example.demo.entities;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Builder
@Getter

public class AccountEntity {
    @Id
    private UUID id;

    //@JoinColumn(name = "userId")
    //@OneToOne(fetch = FetchType.LAZY)
    //private UserEntity userEntity;

    private String password;

    private String email;
}
