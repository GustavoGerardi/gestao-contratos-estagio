package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "student-users")
public class StudentUserEntity extends UserEntity {
    private String ra;

    @OneToMany(mappedBy = "id")
    private final List<Process> processes = new ArrayList<>();

    public StudentUserEntity(UUID id, String name, String ra) {
        super(id, name);
        this.ra = ra;
    }
}
