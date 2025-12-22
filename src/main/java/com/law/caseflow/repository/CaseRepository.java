package com.law.caseflow.repository;

import com.law.caseflow.domain.entity.CaseFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CaseRepository extends JpaRepository<CaseFile, UUID> {
    CaseFile findByCaseNumber(String caseNumber);
}
