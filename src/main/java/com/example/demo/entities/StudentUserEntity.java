package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student-users")
public class StudentUserEntity extends UserEntity {
    private String ra;

    @OneToMany(mappedBy = "id")
    private final List<Process> processes = new ArrayList<>();

    public StudentUserEntity(String name, String ra) {
        super(name);
        this.ra = ra;
    }
}
