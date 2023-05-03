package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TB_USERS")
public abstract class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "postedBy")
    private final List<Document> documents = new ArrayList<>();

    public UserEntity(String name) {
        this.name = name;
    }

    public UserEntity(){}
}
