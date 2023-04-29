package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "", allocationSize = 1)
    private Long id;
    private String name;

    public UserEntity(String name) {
        this.name = name;
    }

    public UserEntity(){}
}
