package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.CreateExpenseModel;
import com.bilgeadam.rabbitmq.model.CreatePermissionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionProducer {

    @Value("${rabbitmq.exchange-employee}")
    private String exchange;

    @Value("${rabbitmq.password-permission-key}")
    private String createPermissionBindingKey;

    private final RabbitTemplate rabbitTemplate;

    public void createPermission(CreatePermissionModel model){
        rabbitTemplate.convertAndSend(exchange,createPermissionBindingKey,model);

    }
}
