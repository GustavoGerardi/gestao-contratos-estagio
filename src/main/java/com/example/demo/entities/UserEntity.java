package com.example.demo.entities;

import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Getter
public abstract class UserEntity {

    @Id
    private UUID id;
    private final String name;

    public UserEntity(String name) {
        this.name = name;
    }
}
