package com.example.demo.services;

import com.example.demo.dto.request.EmailDto;
import com.example.demo.dto.request.UserDataAccount;
import com.example.demo.entities.AccountEntity;
import com.example.demo.entities.StudentUserEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.EmailRepository;
import com.example.demo.repositories.StudentUserRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utils.GeneratePassword;
import jakarta.mail.MessagingException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private GeneratePassword generateRandomPassword;
    @Autowired
    private EmailSenderService emailSender;

    @Autowired
    private StudentUserRepository studentUserRepository;

    @Autowired
    private EmailRepository emailRepository;

    public AccountEntity createStudent(UserDataAccount userDataAccount) throws MessagingException {

        if (accountRepository.findByEmail(userDataAccount.getEmail()) != null)
            throw new RuntimeException("Email already exists.");

        StudentUserEntity studentUser = new StudentUserEntity(userDataAccount.getName(), userDataAccount.getRa());
        String password = generateRandomPassword.generateRandomPassword();

        AccountEntity account = new AccountEntity(studentUser.getId(), studentUser, password, userDataAccount.getEmail());

        EmailDto email = new EmailDto(userDataAccount.getName(), userDataAccount.getEmail(), password);

        emailSender.sendAccountCreationEmail(email);

        return accountRepository.save(account);
    }

    public AccountEntity getStudentAccountByRa(String ra) {
        UserEntity user = studentUserRepository.findByRa(ra);
        return accountRepository.findById(user.getId()).get();
    }

    public List<AccountEntity> getAllStudentAccounts() {
        List<StudentUserEntity> users = studentUserRepository.findAll();
        List<AccountEntity> accountList = new ArrayList<>();

        for (StudentUserEntity studentUser : users) {
            Long accountId = studentUser.getId();
            Optional<AccountEntity> account = accountRepository.findById(accountId);
            account.ifPresent(accountList::add);
        }
        return accountList;
    }

    public AccountEntity updatePassword(String ra, String password) {
        UserEntity user = studentUserRepository.findByRa(ra);

        AccountEntity account = accountRepository.findById(user.getId()).get();
        account.setPassword(password);
        return accountRepository.save(account);
    }
}
