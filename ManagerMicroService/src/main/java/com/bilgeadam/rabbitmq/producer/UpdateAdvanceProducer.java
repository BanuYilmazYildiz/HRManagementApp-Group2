package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.UpdateAdvanceStatusModel;
import com.bilgeadam.rabbitmq.model.UpdateExpenseStatusModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAdvanceProducer {

    @Value("${rabbitmq.exchange-manager}")
    private String exchange;

    @Value("${rabbitmq.update-advance-key}")
    private String updateAdvanceBindingKey;

    private final RabbitTemplate rabbitTemplate;

    public void updateAdvance(UpdateAdvanceStatusModel model){
        rabbitTemplate.convertAndSend(exchange,updateAdvanceBindingKey,model);
    }
}
