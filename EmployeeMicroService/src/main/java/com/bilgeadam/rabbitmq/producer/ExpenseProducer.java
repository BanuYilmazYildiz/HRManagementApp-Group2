package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.CreateExpenseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseProducer {

    @Value("${rabbitmq.exchange-employee}")
    private String exchange;

    @Value("${rabbitmq.password-expense-key}")
    private String createExpenseBindingKey;

    private final RabbitTemplate rabbitTemplate;

    public void createExpense(CreateExpenseModel model){
        rabbitTemplate.convertAndSend(exchange,createExpenseBindingKey,model);

    }

}
