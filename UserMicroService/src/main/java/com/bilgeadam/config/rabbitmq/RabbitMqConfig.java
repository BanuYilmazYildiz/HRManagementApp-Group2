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
    @Value("${rabbitmq.exchange-user}")
    private String exchange;
//    @Value("${rabbitmq.register-key}")
//    private String registerBindingKey;
//    @Value("${rabbitmq.queue-register}")
//    private String queueNameRegister;
    @Value("${rabbitmq.register-mail-key}")
    private String registerMailBindingKey;
    @Value("${rabbitmq.register-mail-queue}")
    private String registerMailQueue;

    @Value("${rabbitmq.password-reset-mail-key}")
    private String passwordResetMailBindingKey;

    @Value("${rabbitmq.password-reset-mail-queue}")
    private String passwordResetMailQueue;


    @Bean
    public DirectExchange exchangeUser(){
        return new DirectExchange(exchange); // user exchange
    }

//    @Bean
//    public Queue registerQueue(){
//        return new Queue(queueNameRegister);
//        // register metodu icin yazıldı ondan ismi bu sıra gereken her seyde onlara ait olusturulacak
//        // 15672 de register-queue olarak gozukecek
//    }

//    @Bean
//    public Binding bindingRegister(final Queue registerQueue , DirectExchange exchangeUser){
//        return BindingBuilder.bind(registerQueue).to(exchangeUser).with(registerBindingKey);
//    }

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
    @Bean
    public Binding bindingRegisterMail(final Queue registerMailQueue ,DirectExchange exchangeUser){
        return BindingBuilder.bind(registerMailQueue).to(exchangeUser).with(registerMailBindingKey);
    }

    @Bean
    public Binding bindingPasswordResetMail(final Queue passwordResetMailQueue ,DirectExchange exchangeUser){
        return BindingBuilder.bind(passwordResetMailQueue).to(exchangeUser).with(passwordResetMailBindingKey);
    }


}
