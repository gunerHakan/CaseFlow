package com.law.caseflow.dto;

import java.util.UUID;

public record CaseResponse(
        UUID id,
        String caseNumber,
        String title,
        String description,
        String status,
        UUID clientId
) {}
