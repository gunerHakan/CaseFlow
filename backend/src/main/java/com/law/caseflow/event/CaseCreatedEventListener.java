package com.law.caseflow.event;

import com.law.caseflow.service.producer.CaseProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class CaseCreatedEventListener {

    private final CaseProducer caseProducer;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onCaseCreatedAfterCommit(CaseCreatedEvent event) {
        try {
            caseProducer.sendCaseCreatedEvent(event);
        } catch (Exception e) {
            // Commit olduktan sonra mesaj gönderimi başarısız olabilir.
            // Bu noktada DB transaction'ı geri alamayız; loglayıp daha sonra retry/outbox gibi
            // mekanizmalarla güçlendirmek gerekir.
            log.error("Failed to send RabbitMQ message AFTER_COMMIT for case: {}", event.caseNumber(), e);
        }
    }
}

