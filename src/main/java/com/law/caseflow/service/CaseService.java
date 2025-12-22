package com.law.caseflow.service;

import com.law.caseflow.domain.entity.CaseFile;
import com.law.caseflow.domain.entity.Client;
import com.law.caseflow.domain.enums.CaseStatus;
import com.law.caseflow.dto.CaseCreateRequest;
import com.law.caseflow.dto.CaseResponse;
import com.law.caseflow.exception.NotFoundException;
import com.law.caseflow.repository.CaseRepository;
import com.law.caseflow.service.mapper.CaseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CaseService {

    private final CaseRepository caseRepository;

    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Transactional
    public CaseResponse create(CaseCreateRequest request, Client client) {
        CaseFile entity = CaseMapper.toEntity(request);
        entity.setStatus(CaseStatus.OPEN);
        entity.setClient(client);

        CaseFile saved = caseRepository.save(entity);
        return CaseMapper.toResponse(saved);
    }

    public CaseFile getEntityByCaseNumber(String caseNumber) {
        CaseFile entity = caseRepository.findByCaseNumber(caseNumber);
        if (entity == null) {
            throw new NotFoundException("Case not found: " + caseNumber);
        }
        return entity;
    }
}
