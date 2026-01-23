package com.law.caseflow.service.mapper;

import com.law.caseflow.domain.entity.CaseFile;
import com.law.caseflow.dto.CaseCreateRequest;
import com.law.caseflow.dto.CaseResponse;

public class CaseMapper {

    public static CaseFile toEntity(CaseCreateRequest request) {
        CaseFile caseFile = new CaseFile();
        caseFile.setCaseNumber(request.caseNumber());
        caseFile.setTitle(request.title());
        caseFile.setDescription(request.description());
        return caseFile;
    }

    public static CaseResponse toResponse(CaseFile entity) {
        return new CaseResponse(
                entity.getId(),
                entity.getCaseNumber(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus().name(),
                entity.getClient() != null ? entity.getClient().getId() : null
        );
    }
}
