package com.law.caseflow.dto;

import java.io.Serializable;
import java.util.UUID;

public record CaseResponse(
        UUID id,
        String caseNumber,
        String title,
        String description,
        String status,
        UUID clientId
) implements Serializable {
    private static final long serialVersionUID = 1L;
}
