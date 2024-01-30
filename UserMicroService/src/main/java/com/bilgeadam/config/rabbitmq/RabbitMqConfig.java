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
    @Value("${rabbitmq.register-key}")
    private String registerBindingKey;
    @Value("${rabbitmq.queue-register}")
    private String queueNameRegister;
    @Value("${rabbitmq.register-mail-key}")
    private String registerMailBindingKey;
    @Value("${rabbitmq.register-mail-queue}")
    private String registerMailQueue;

    @Bean
    public DirectExchange exchangeAuth(){
        return new DirectExchange(exchange); // user exchange
    }

    @Bean
    public Queue registerQueue(){
        return new Queue(queueNameRegister);
        // register metodu icin yazıldı ondan ismi bu sıra gereken her seyde onlara ait olusturulacak
        // 15672 de register-queue olarak gozukecek
    }

    @Bean
    public Binding bindingRegister(final Queue registerQueue , DirectExchange exchangeAuth){
        return BindingBuilder.bind(registerQueue).to(exchangeAuth).with(registerBindingKey);
    }

    @Bean
    public Queue registerMailQueue(){
        return new Queue(registerMailQueue);
        // register metodu icin yazıldı ondan ismi bu sıra gereken her seyde onlara ait olusturulacak
        // 15672 de register-queue olarak gozukecek
    }
    @Bean
    public Binding bindingRegisterMail(final Queue registerMailQueue ,DirectExchange exchangeAuth){
        return BindingBuilder.bind(registerMailQueue).to(exchangeAuth).with(registerMailBindingKey);
    }


}
