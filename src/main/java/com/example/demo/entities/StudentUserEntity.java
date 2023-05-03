package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TB_STUDENT")
public class StudentUserEntity extends UserEntity {
    private String ra;

    @OneToMany(mappedBy = "id")
    private final List<ProcessEntity> processes = new ArrayList<>();

    public StudentUserEntity(String name, String ra) {
        super(name);
        this.ra = ra;
    }

    public StudentUserEntity() {
    }
}
