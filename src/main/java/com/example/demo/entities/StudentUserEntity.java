package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Data
@Table(name = "TB_STUDENT")
public class StudentUserEntity extends UserEntity {
    private String ra;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private final List<Process> processes = new ArrayList<>();

    public StudentUserEntity(String name, String ra) {
        super(name);
        this.ra = ra;
    }

    public StudentUserEntity(){}
}
