package com.example.demo.services;

import com.example.demo.dto.request.UserDataAccount;
import com.example.demo.entities.AccountEntity;
import com.example.demo.entities.StudentUserEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.utils.GeneratePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public class CreateStudentService {

    @Autowired
    private AccountRepository accountRepository;

    private GeneratePassword generateRandomPassword;

    public AccountEntity createStudent(UserDataAccount userDataAccount) {
        StudentUserEntity studentUser = new StudentUserEntity(UUID.randomUUID(), userDataAccount.getName(), userDataAccount.getRa());
        String password = generateRandomPassword.generateRandomPassword();
        AccountEntity account = AccountEntity.builder()
                .id(studentUser.getId())
                .userEntity(studentUser)
                .password(password)
                .email(userDataAccount.getEmail())
                .build();

        return accountRepository.save(account);
    }
}
