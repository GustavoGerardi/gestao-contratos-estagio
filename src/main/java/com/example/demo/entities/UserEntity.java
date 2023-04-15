package com.example.demo.entities;

import javax.persistence.Id;
import java.util.UUID;

public abstract class UserEntity {
    @Id
    private UUID id;
    private String name;
}
