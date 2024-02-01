package com.bilgeadam.service;

import com.bilgeadam.rabbitmq.model.PasswordResetModel;
import com.bilgeadam.rabbitmq.model.RegisterMailModel;
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
        mailMessage.setText("Sayin " +model.getName() + " " + model.getSurname() + ",\n" + model.getCompany() + " sirketimize hosgeldiniz.\nİş mailiniz : "
        + model.getEmail() + "\nTek kullanımlık sifreniz : " + model.getActivationCode()
        );
        javaMailSender.send(mailMessage);
    }

    public void sendPasswordResetMail(PasswordResetModel model){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("grup2java11@gmail.com");
        mailMessage.setTo(model.getPersonalEmail());
        mailMessage.setSubject("Password resetleme kodunuz : ");
        mailMessage.setText("Sayin " +model.getName() + " " + model.getSurname() + "," +
                "\nTek kullanımlık sifreniz : " + model.getActivationCode()
        );
        javaMailSender.send(mailMessage);
    }
}
