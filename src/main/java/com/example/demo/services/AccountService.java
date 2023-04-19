package com.example.demo.services;

import com.example.demo.dto.request.UserDataAccount;
import com.example.demo.entities.AccountEntity;
import com.example.demo.entities.AdminUserEntity;
import com.example.demo.entities.StudentUserEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public String createAccount(UserDataAccount userDataAccount) throws Exception {

        UserEntity userEntity;

        if (Objects.isNull(userDataAccount.getRa()) && Objects.nonNull(userDataAccount.getCollaboratorCode())) {
            userEntity = new AdminUserEntity(userDataAccount.getName(), userDataAccount.getCollaboratorCode());
        } else if (Objects.isNull(userDataAccount.getCollaboratorCode()) && Objects.nonNull(userDataAccount.getRa())) {
            userEntity = new StudentUserEntity(userDataAccount.getName(), userDataAccount.getRa());
        } else {
            throw new Exception();
        }

        AccountEntity accountEntity = AccountEntity.builder()
                .id(UUID.randomUUID())
                .email(userDataAccount.getEmail())
                .password(userDataAccount.getPassword())
                //.userEntity(userEntity)
                .build();

        //AccountEntity accountCreated = accountRepository.save(accountEntity);

        return "Sucesso";
    }
}
