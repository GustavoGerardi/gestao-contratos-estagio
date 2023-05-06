package com.example.demo.repositories;

import com.example.demo.entities.StudentUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentUserRepository extends JpaRepository<StudentUserEntity, Long> {

    StudentUserEntity findByRa(String ra);
}
