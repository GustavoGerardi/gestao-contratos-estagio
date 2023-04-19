package com.example.demo.entities;

import jakarta.persistence.Table;

//@Getter
//@Entity
@Table(name = "students")
public class StudentUserEntity extends UserEntity {
    private final String ra;

    public StudentUserEntity(String name, String ra) {
        super(name);
        this.ra = ra;
    }
}
