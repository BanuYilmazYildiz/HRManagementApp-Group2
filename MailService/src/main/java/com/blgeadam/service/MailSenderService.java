package com.blgeadam.service;

import com.blgeadam.rabbitmq.model.RegisterMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender javaMailSender;

    public void sendMail(RegisterMailModel model){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("grup2java11@gmail.com");
        mailMessage.setTo(model.getPersonalEmail());
        mailMessage.setSubject("HrManagementApp Aktivasyon Kodunuz");
        mailMessage.setText("Sayin " +model.getName() + " " + model.getSurname() + ", " + model.getCompany() + " sirketimize hosgeldiniz.İş mailiniz"
        + model.getEmail() + " tek kullanımlık sifreniz " + model.getActivationCode()
        );
        javaMailSender.send(mailMessage);
    }
}
