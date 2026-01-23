package com.law.caseflow.auth;

public record LoginRequest(
        String username,
        String password
) {}
