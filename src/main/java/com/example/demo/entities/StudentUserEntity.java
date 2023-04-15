package com.example.demo.entities;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Entity()
@Table(name = "students")
public class StudentUserEntity extends UserEntity {
    private final String ra;

    public StudentUserEntity(String name, String ra) {
        super(name);
        this.ra = ra;
    }
}
