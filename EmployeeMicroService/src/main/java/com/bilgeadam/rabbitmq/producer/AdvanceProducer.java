package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.CreateAdvanceModel;
import com.bilgeadam.rabbitmq.model.CreatePermissionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvanceProducer {

    @Value("${rabbitmq.exchange-employee}")
    private String exchange;

    @Value("${rabbitmq.password-advance-key}")
    private String createAdvanceBindingKey;

    private final RabbitTemplate rabbitTemplate;

    public void createAdvance(CreateAdvanceModel model){
        rabbitTemplate.convertAndSend(exchange,createAdvanceBindingKey,model);

    }
}
