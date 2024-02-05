package com.bilgeadam.config.rabbitmq;


import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {



    @Value("${rabbitmq.queue-expense-queue}")
    private String expenseQueue;


    @Value("${rabbitmq.queue-permission-queue}")
    private String permissionQueue;

    @Value("${rabbitmq.queue-advance-queue}")
    private String advanceQueue;

    @Bean
    public Queue createExpenseQueue(){
       return new Queue(expenseQueue);
    }

    @Bean
    public Queue createPermissionQueue(){
        return new Queue(permissionQueue);
    }

    @Bean
    public Queue createAdvanceQueue(){
        return new Queue(advanceQueue);
    }

}
