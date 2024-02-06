package com.bilgeadam.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.exchange-employee}")
    private String exchange;

    @Value("${rabbitmq.queue-expense-queue}")
    private String expenseQueue;

    @Value("${rabbitmq.password-expense-key}")
    private String createExpenseBindingKey;

    @Value("${rabbitmq.queue-permission-queue}")
    private String permissionQueue;

    @Value("${rabbitmq.password-permission-key}")
    private String createPermissionBindingKey;

    @Value("${rabbitmq.queue-advance-queue}")
    private String advanceQueue;

    @Value("${rabbitmq.password-advance-key}")
    private String createAdvanceBindingKey;

    @Value("${rabbitmq.queue-update-expense-queue}")
    private String expenseUpdateQueue;

    @Value("${rabbitmq.queue-update-advance-queue}")
    private String advanceUpdateQueue;

    @Value("${rabbitmq.queue-update-permission-queue}")
    private String permissionUpdateQueue;




   @Bean
    public DirectExchange exchangeEmployee(){
        return new DirectExchange(exchange);
    }

    @Bean
    public Queue createExpenseQueue(){
       return new Queue(expenseQueue);
    }

    @Bean
    public Binding bindingCreateExpense(final Queue createExpenseQueue , final DirectExchange exchangeEmployee){
        return BindingBuilder.bind(createExpenseQueue).to(exchangeEmployee).with(createExpenseBindingKey);
    }

    @Bean
    public Queue createPermissionQueue(){
        return new Queue(permissionQueue);
    }
    @Bean
    public Binding bindingCreatePermission(final Queue createPermissionQueue , DirectExchange exchangeEmployee){
        return BindingBuilder.bind(createPermissionQueue).to(exchangeEmployee).with(createPermissionBindingKey);
    }



    @Bean
    public Queue createAdvanceQueue(){
        return new Queue(advanceQueue);
    }

    @Bean
    public Binding bindingCreateAdvance(final Queue createAdvanceQueue , DirectExchange exchangeEmployee){
        return BindingBuilder.bind(createAdvanceQueue).to(exchangeEmployee).with(createAdvanceBindingKey);
    }

    @Bean
    public Queue updateExpenseQueue(){
        return new Queue(expenseUpdateQueue);
    }

    @Bean
    public Queue updateAdvanceQueue(){
        return new Queue(advanceUpdateQueue);
    }

    @Bean
    public Queue updatePermissionQueue(){
        return new Queue(permissionUpdateQueue);
    }


}
