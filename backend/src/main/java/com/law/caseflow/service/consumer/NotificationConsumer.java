package com.law.caseflow.service.consumer;

import com.law.caseflow.config.RabbitMqConfig;
import com.law.caseflow.event.CaseCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void handleCaseCreatedEvent(CaseCreatedEvent event) {
        log.info("📥 Received message from RabbitMQ: {}", event);

        // Simülasyon: Email gönderme işlemi
        sendEmail(event.clientEmail(), event.caseNumber());
    }

    private void sendEmail(String email, String caseNumber) {
        try {
            // Email atıyormuş gibi 2 saniye bekle (Asenkronluğun faydasını görmek için)
            Thread.sleep(2000);
            log.info("📧 Email sent to {} for case {}", email, caseNumber);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Error sending email", e);
        }
    }
}
