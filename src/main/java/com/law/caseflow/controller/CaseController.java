package com.law.caseflow.controller;

import com.law.caseflow.domain.entity.CaseFile;
import com.law.caseflow.dto.CaseCreateRequest;
import com.law.caseflow.dto.CaseResponse;
import com.law.caseflow.exception.UnauthorizedException;
import com.law.caseflow.service.CaseService;
import com.law.caseflow.service.ClientService;
import com.law.caseflow.service.mapper.CaseMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cases")
public class CaseController {

    private final CaseService caseService;
    private final ClientService clientService;

    public CaseController(CaseService caseService, ClientService clientService) {
        this.caseService = caseService;
        this.clientService = clientService;
    }

    @PostMapping
    public CaseResponse create(@Valid @RequestBody CaseCreateRequest request,
                               @RequestParam UUID clientId) {
        return caseService.create(request, clientService.getEntityById(clientId));
    }

    @GetMapping("/{caseNumber}")
    public CaseResponse getByCaseNumber(@PathVariable String caseNumber,
                                        @RequestParam UUID clientId) {
        CaseFile caseFile = caseService.getEntityByCaseNumber(caseNumber);
        if (!caseFile.getClient().getId().equals(clientId)) {
            throw new UnauthorizedException("You are not allowed to view this case.");
        }

        return CaseMapper.toResponse(caseFile);
    }
}
