package com.example.demo.services;

import com.example.demo.repositories.AdminUserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminService {

    @Autowired
    AdminUserRepository adminUserRepository;

    @SneakyThrows
    public Boolean isAdminUser(Long id) {
        return Objects.nonNull(adminUserRepository.findById(id)
                .orElseThrow(() -> new Exception("Admin user does not exist.")));
    }
}
