package com.law.caseflow.service;

import com.law.caseflow.domain.entity.CaseFile;
import com.law.caseflow.domain.entity.Client;
import com.law.caseflow.domain.enums.CaseStatus;
import com.law.caseflow.dto.CaseCreateRequest;
import com.law.caseflow.dto.CaseResponse;
import com.law.caseflow.exception.NotFoundException;
import com.law.caseflow.repository.CaseRepository;
import com.law.caseflow.service.mapper.CaseMapper;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CaseService {

    private final CaseRepository caseRepository;

    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Transactional
    @CacheEvict(value = "all_cases", allEntries = true)
    public CaseResponse create(CaseCreateRequest request, Client client) {
        CaseFile entity = CaseMapper.toEntity(request);
        entity.setStatus(CaseStatus.OPEN);
        entity.setClient(client);

        CaseFile saved = caseRepository.save(entity);
        return CaseMapper.toResponse(saved);
    }

    @Cacheable(value = "all_cases")
    public List<CaseResponse> getAllCases() {
        return findAllCases();
    }

    @Cacheable(value = "cases", key = "#caseNumber")
    public CaseFile getEntityByCaseNumber(String caseNumber) {
        System.out.println("⚠️ DB'den çekiliyor... Case No: " + caseNumber);

        CaseFile entity = caseRepository.findByCaseNumber(caseNumber);
        if (entity == null) {
            throw new NotFoundException("Case not found: " + caseNumber);
        }
        return entity;
    }

    public List<CaseResponse> findAllCases() {
        return caseRepository.findAll()
                .stream()
                .map(CaseMapper::toResponse)
                .toList();
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "cases", key = "#caseNumber"),
            @CacheEvict(value = "all_cases", allEntries = true)
    })
    public CaseResponse update(String caseNumber, CaseCreateRequest request) {
        CaseFile caseFile = getEntityByCaseNumber(caseNumber);

        caseFile.setTitle(request.title());
        caseFile.setDescription(request.description());

        CaseFile updatedCase = caseRepository.save(caseFile);
        return CaseMapper.toResponse(updatedCase);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "cases", key = "#caseNumber"),
            @CacheEvict(value = "all_cases", allEntries = true)
    })
    public void delete(String caseNumber) {
        CaseFile caseFile = getEntityByCaseNumber(caseNumber);
        caseRepository.delete(caseFile);
    }
}
