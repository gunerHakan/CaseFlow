package com.law.caseflow.event;

import java.io.Serializable;
import java.time.LocalDateTime;

public record CaseCreatedEvent(
        String caseNumber,
        String clientEmail,
        String title,
        LocalDateTime createdAt
) implements Serializable {
}
