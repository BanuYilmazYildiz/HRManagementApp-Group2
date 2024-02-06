package com.bilgeadam.rabbitmq.producer;


import com.bilgeadam.rabbitmq.model.UpdateExpenseStatusModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateExpenseProducer {

    @Value("${rabbitmq.exchange-manager}")
    private String exchange;

    @Value("${rabbitmq.update-expense-key}")
    private String updateExpenseBindingKey;

    private final RabbitTemplate rabbitTemplate;

    public void updateExpense(UpdateExpenseStatusModel model){
        rabbitTemplate.convertAndSend(exchange,updateExpenseBindingKey,model);
    }
}
