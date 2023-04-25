package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Getter
@Entity
@Table(name = "usuarios")
@Builder
@AllArgsConstructor
public class User {


    @Id
    private UUID id;

    private String name;
}
