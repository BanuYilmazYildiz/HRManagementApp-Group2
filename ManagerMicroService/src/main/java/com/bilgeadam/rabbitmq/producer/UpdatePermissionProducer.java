package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.UpdateAdvanceStatusModel;
import com.bilgeadam.rabbitmq.model.UpdatePermissionStatusModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePermissionProducer {

    @Value("${rabbitmq.exchange-manager}")
    private String exchange;

    @Value("${rabbitmq.update-permission-key}")
    private String updatePermissionBindingKey;

    private final RabbitTemplate rabbitTemplate;

    public void updatePermission(UpdatePermissionStatusModel model){
        rabbitTemplate.convertAndSend(exchange,updatePermissionBindingKey,model);
    }
}
