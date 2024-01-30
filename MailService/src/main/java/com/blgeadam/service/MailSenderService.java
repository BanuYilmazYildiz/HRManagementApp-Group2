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
        mailMessage.setFrom("${java11mailusername}");
        mailMessage.setTo(model.getEmail());
        mailMessage.setSubject("HrManagementApp Aktivasyon Kodunuz");
        mailMessage.setText("Hesap Dogrulama Kodunuz : " + model.getActivationCode());
        javaMailSender.send(mailMessage);
    }
}
