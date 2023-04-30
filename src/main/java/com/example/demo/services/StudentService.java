package com.example.demo.services;

import com.example.demo.dto.request.UserDataAccount;
import com.example.demo.entities.AccountEntity;
import com.example.demo.entities.StudentUserEntity;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utils.GeneratePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private GeneratePassword generateRandomPassword;
    @Autowired
    private EmailSenderService emailSender;

    public AccountEntity createStudent(UserDataAccount userDataAccount) {

        StudentUserEntity studentUser = new StudentUserEntity(userDataAccount.getName(), userDataAccount.getRa());
        String password = generateRandomPassword.generateRandomPassword();

//        userRepository.save(studentUser);

        AccountEntity account = new AccountEntity(studentUser.getId(), studentUser, password, userDataAccount.getEmail());

        emailSender.sendEmail(password);

        return accountRepository.save(account);
    }

    public AccountEntity getStudentById(Long id) {
        return accountRepository.findById(id).get();
    }

    public List<AccountEntity> getAllStudents() {
        return accountRepository.findAll();
    }
}
