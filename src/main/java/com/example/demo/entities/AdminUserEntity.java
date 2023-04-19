package com.example.demo.entities;

import lombok.Getter;


//@Entity
//@Table(name = "admins")
@Getter
public class AdminUserEntity extends UserEntity {
    private final String collaboratorCode;

    public AdminUserEntity(String name, String collaboratorCode) {
        super(name);
        this.collaboratorCode = collaboratorCode;
    }
}
