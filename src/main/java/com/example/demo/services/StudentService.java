package com.example.demo.services;

import com.example.demo.dto.request.UserDataAccount;
import com.example.demo.entities.AccountEntity;
import com.example.demo.entities.StudentUserEntity;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.utils.GeneratePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private AccountRepository accountRepository;

    private GeneratePassword generateRandomPassword;

    private EmailSenderService emailSender;

    public AccountEntity createStudent(UserDataAccount userDataAccount) {
        StudentUserEntity studentUser = new StudentUserEntity(UUID.randomUUID(), userDataAccount.getName(), userDataAccount.getRa());
        String password = generateRandomPassword.generateRandomPassword();
        AccountEntity account = AccountEntity.builder()
                .id(studentUser.getId())
                .userEntity(studentUser)
                .password(password)
                .email(userDataAccount.getEmail())
                .build();

        emailSender.sendEmail(password);

        return accountRepository.save(account);
    }

    public AccountEntity getStudentById(UUID id) {
        return accountRepository.findById(id).get();
    }
}
