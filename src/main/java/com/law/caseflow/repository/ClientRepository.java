package com.law.caseflow.repository;

import com.law.caseflow.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    boolean existsByEmail(String email);
    Optional<Client> findByEmail(String email);
}
