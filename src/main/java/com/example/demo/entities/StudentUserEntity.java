package com.example.demo.entities;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;


@Builder
@Getter
@Entity
public class StudentUserEntity extends UserEntity {
    private String ra;
}
