package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "admin-users")
public class AdminUserEntity extends UserEntity {
    private String collaboratorCode;

    public AdminUserEntity(UUID id, String name, String collaboratorCode) {
        super(id, name);
        this.collaboratorCode = collaboratorCode;
    }
}
