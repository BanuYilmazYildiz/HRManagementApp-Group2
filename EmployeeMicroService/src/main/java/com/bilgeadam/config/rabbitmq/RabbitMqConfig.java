//package com.bilgeadam.config.rabbitmq;
//
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMqConfig {
//
//    @Value("${rabbitmq.exchange-employee}")
//    private String exchange;
//
//    @Value("${rabbitmq.queue-register}")
//    private String queueNameRegister;
//    @Bean
//    public DirectExchange exchangeUser(){
//        return new DirectExchange(exchange);
//    }
//
//
//    @Bean
//    public Queue registerQueue(){
//        return new Queue(queueNameRegister);
//    }
//
//
//
//}
