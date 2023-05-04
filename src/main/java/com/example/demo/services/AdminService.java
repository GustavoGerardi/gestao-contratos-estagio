package com.example.demo.services;

import com.example.demo.repositories.AdminUserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminUserRepository adminUserRepository;

    @SneakyThrows
    public Boolean isAdminUser(Long id) {
        return adminUserRepository.findById(id).isPresent();
    }
}
