package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public UserEntity(String name) {
        this.name = name;
    }

    public UserEntity(){}
}
