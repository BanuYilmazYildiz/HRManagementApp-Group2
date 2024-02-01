package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.PasswordResetModel;

import com.bilgeadam.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordResetConsumer {

    private final MailSenderService mailSenderService;
    @RabbitListener(queues = "${rabbitmq.password-reset-mail-queue}")
    public void sendActivationCode(PasswordResetModel model){
        log.info("model{}",model.toString());
        mailSenderService.sendPasswordResetMail(model);
    }
}
