package com.law.caseflow.dto;

import jakarta.validation.constraints.NotBlank;

public record CaseCreateRequest(
        @NotBlank String caseNumber,
        @NotBlank String title,
        String description
) {}
