package com.example.demo.entities;

import lombok.Getter;

import javax.persistence.Id;
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
