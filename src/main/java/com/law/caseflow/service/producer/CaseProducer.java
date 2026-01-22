package com.law.caseflow.service.producer;

import com.law.caseflow.config.RabbitMqConfig;
import com.law.caseflow.event.CaseCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CaseProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendCaseCreatedEvent(CaseCreatedEvent event) {
        log.info("📤 Sending message to RabbitMQ: {}", event);
        
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.EXCHANGE_NAME,
                RabbitMqConfig.ROUTING_KEY,
                event
        );
    }
}
