package com.example.demo.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Transactional
    public void sendEmail(String password) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("gutm86@gmail.com");
        message.setTo("gutm86@hotmail.com");
        message.setSubject("Assunto");
        message.setText(password);
        mailSender.send(message);
    }
}
