package com.law.caseflow.dto;

import java.util.UUID;

public record ClientDto(
        UUID id,
        String firstName,
        String lastName,
        String email
) {}
