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

    @Value("${rabbitmq.exchange-manager}")
    private String exchange;

    @Value("${rabbitmq.queue-expense-queue}")
    private String expenseQueue;


    @Value("${rabbitmq.queue-permission-queue}")
    private String permissionQueue;

    @Value("${rabbitmq.queue-advance-queue}")
    private String advanceQueue;


    @Value("${rabbitmq.queue-update-expense-queue}")
    private String expenseUpdateQueue;

    @Value("${rabbitmq.update-expense-key}")
    private String updateExpenseBindingKey;

    @Value("${rabbitmq.queue-update-advance-queue}")
    private String advanceUpdateQueue;

    @Value("${rabbitmq.update-advance-key}")
    private String updateAdvanceBindingKey;


    @Value("${rabbitmq.queue-update-permission-queue}")
    private String permissionUpdateQueue;

    @Value("${rabbitmq.update-permission-key}")
    private String updatePermissionBindingKey;

    @Bean
    public DirectExchange exchangeManager(){
        return new DirectExchange(exchange);
    }


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


    @Bean
    public Queue updateExpenseQueue(){
        return new Queue(expenseUpdateQueue);
    }

    @Bean
    public Binding bindingUpdateExpense(final Queue updateExpenseQueue , final DirectExchange exchangeManager){
        return BindingBuilder.bind(updateExpenseQueue).to(exchangeManager).with(updateExpenseBindingKey);
    }

    @Bean
    public Queue updateAdvanceQueue(){
        return new Queue(advanceUpdateQueue);
    }

    @Bean
    public Binding bindingUpdateAdvance(final Queue updateAdvanceQueue , final DirectExchange exchangeManager){
        return BindingBuilder.bind(updateAdvanceQueue).to(exchangeManager).with(updateAdvanceBindingKey);
    }

    @Bean
    public Queue updatePermissionQueue(){
        return new Queue(permissionUpdateQueue);
    }

    @Bean
    public Binding bindingUpdatePermission(final Queue updatePermissionQueue , final DirectExchange exchangeManager){
        return BindingBuilder.bind(updatePermissionQueue).to(exchangeManager).with(updatePermissionBindingKey);
    }
}
