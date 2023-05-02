package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TB_USERS")
public abstract class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String name;

    @OneToMany(mappedBy = "postedBy")
    private final List<Document> documents = new ArrayList<>();

    public UserEntity(String name) {
        this.name = name;
    }
}
