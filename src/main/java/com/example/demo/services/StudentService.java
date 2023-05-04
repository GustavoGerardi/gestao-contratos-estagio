package com.example.demo.services;

import com.example.demo.dto.request.EmailDto;
import com.example.demo.dto.request.UserDataAccount;
import com.example.demo.entities.AccountEntity;
import com.example.demo.entities.StudentUserEntity;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.EmailRepository;
import com.example.demo.repositories.StudentUserRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utils.GeneratePassword;
import jakarta.mail.MessagingException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private EmailRepository emailRepository;


    @Autowired
    private StudentUserRepository studentUserRepository;

    public AccountEntity createStudent(UserDataAccount userDataAccount) throws MessagingException {

        if (accountRepository.findByEmail(userDataAccount.getEmail()) != null)
            throw new RuntimeException("Email already exists.");

        StudentUserEntity studentUser = new StudentUserEntity(userDataAccount.getName(), userDataAccount.getRa());
        String password = generateRandomPassword.generateRandomPassword();

        AccountEntity account = new AccountEntity(studentUser.getId(), studentUser, password, userDataAccount.getEmail());

        EmailDto email = new EmailDto(userDataAccount.getName(), userDataAccount.getEmail(), password);

        //emailSender.sendEmail(email);

        return accountRepository.save(account);
    }

    public AccountEntity getStudentById(Long id) {
        return accountRepository.findById(id).get();
    }

    public List<AccountEntity> getAllStudents() {
        return accountRepository.findAll();
    }

    @SneakyThrows
    public Boolean isStudentUser(Long id) {
        return studentUserRepository.findById(id).isPresent();
    }
}
