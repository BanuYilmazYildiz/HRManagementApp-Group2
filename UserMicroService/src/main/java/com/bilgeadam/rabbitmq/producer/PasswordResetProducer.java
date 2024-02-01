package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.PasswordResetModel;
import com.bilgeadam.rabbitmq.model.RegisterMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordResetProducer {
    @Value("${rabbitmq.exchange-user}")
    private String directExchange; // takas faktoru

    @Value("${rabbitmq.password-reset-mail-key}")
    private String passwordResetMailBindingKey; // baglayıcı

    private final RabbitTemplate rabbitTemplate;

    public void sendActivationCode(PasswordResetModel model){
        rabbitTemplate.convertAndSend(directExchange,passwordResetMailBindingKey,model);

    }
}
