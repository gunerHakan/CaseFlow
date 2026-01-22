package com.law.caseflow.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String QUEUE_NAME = "case.notification.queue";
    public static final String EXCHANGE_NAME = "case.exchange";
    public static final String ROUTING_KEY = "case.created";

    // 1. Kuyruk Tanımı
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true); // true = durable (RabbitMQ kapanıp açılsa da kuyruk silinmez)
    }

    // 2. Exchange Tanımı (Topic Exchange kullanıyoruz, esnek routing için)
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // 3. Binding (Kuyruk ile Exchange'i bağlıyoruz)
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    // 4. JSON Converter (Mesajları JSON olarak gönderip almak için)
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    // 5. RabbitTemplate (Mesaj göndermek için kullanılan ana sınıf)
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter());
        return template;
    }
}
