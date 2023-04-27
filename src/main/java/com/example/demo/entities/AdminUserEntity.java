package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "admin-users")
public class AdminUserEntity extends UserEntity {
    private String collaboratorCode;

    public AdminUserEntity(String name, String collaboratorCode) {
        super(name);
        this.collaboratorCode = collaboratorCode;
    }
}
