package com.example.demo.services;

import com.example.demo.dto.request.EmailDto;
import com.example.demo.entities.EmailEntity;
import com.example.demo.enums.EmailStatus;
import com.example.demo.repositories.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Transactional
    public void sendAccountCreationEmail(EmailDto emailDto) throws MessagingException {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("thamires.agnes12@gmail.com");
        message.setTo(emailDto.getEmailTo());
        message.setSubject("Bem-vindo ao Sistema Estágio Fatec");
        message.setText("Agora você tem acesso ao Sistema de Estágio Fatec. \n\nOs dados para login são: \n\n" + "Login: " + emailDto.getEmailTo() + "\n" + "Senha Provisória: " + emailDto.getPassword() + "\n\n"
                + "Em caso de dúvidas ou problemas, entre em contato com o responsável pelo e-mail institucional da sua Fatec.");

        mailSender.send(message);

        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setEmailFrom(message.getFrom());
        emailEntity.setEmailTo(emailDto.getEmailTo());
        emailEntity.setText(message.getText());
        emailEntity.setSendDateEmail(LocalDateTime.now());
        emailEntity.setStatusEmail(EmailStatus.SENT);
        emailRepository.save(emailEntity);

    }

    @Transactional
    public void sendUpdateStatusEmail(String email) throws MessagingException {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("thamires.agnes12@gmail.com");
        message.setTo(email);
        message.setSubject("Seu documento foi para ser assinado!");
        message.setText("Seu documento está indo para a mesa do diretor para ser assinado");

        mailSender.send(message);

        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setEmailFrom(message.getFrom());
        emailEntity.setEmailTo(email);
        emailEntity.setText(message.getText());
        emailEntity.setSendDateEmail(LocalDateTime.now());
        emailEntity.setStatusEmail(EmailStatus.SENT);
        emailRepository.save(emailEntity);

    }
}
