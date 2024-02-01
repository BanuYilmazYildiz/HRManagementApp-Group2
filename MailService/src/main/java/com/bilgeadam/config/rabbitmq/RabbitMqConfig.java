package com.bilgeadam.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.register-mail-queue}")
    private String registerMailQueue;

    @Value("${rabbitmq.password-reset-mail-queue}")
    private String passwordResetMailQueue;



    @Bean
    public Queue registerMailQueue(){
        return new Queue(registerMailQueue);
        // register metodu icin yazıldı ondan ismi bu sıra gereken her seyde onlara ait olusturulacak
        // 15672 de register-queue olarak gozukecek
    }

    @Bean
    public Queue passwordResetMailQueue(){
        return new Queue(passwordResetMailQueue);
        // register metodu icin yazıldı ondan ismi bu sıra gereken her seyde onlara ait olusturulacak
        // 15672 de register-queue olarak gozukecek
    }
}
