package com.law.caseflow.controller;

import com.law.caseflow.domain.entity.CaseFile;
import com.law.caseflow.domain.entity.Client;
import com.law.caseflow.dto.CaseCreateRequest;
import com.law.caseflow.dto.CaseResponse;
import com.law.caseflow.exception.NotFoundException;
import com.law.caseflow.exception.UnauthorizedException;
import com.law.caseflow.service.CaseService;
import com.law.caseflow.service.ClientService;
import com.law.caseflow.service.mapper.CaseMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        Client client = clientService.getEntityById(clientId);
        if (client == null) {
            throw new NotFoundException("Client not found with id: " + clientId);
        }
        return caseService.create(request, client);
    }

    @GetMapping
    public List<CaseResponse> getAllCases() {
        return caseService.getAllCases();
    }

    @GetMapping("/{caseNumber}")
    public CaseResponse getByCaseNumber(@PathVariable String caseNumber,
                                        @RequestParam UUID clientId) {
        CaseFile caseFile = caseService.getEntityByCaseNumber(caseNumber);

        if (caseFile.getClient() == null || !caseFile.getClient().getId().equals(clientId)) {
            throw new UnauthorizedException("You are not allowed to view this case.");
        }

        return CaseMapper.toResponse(caseFile);
    }

    @GetMapping("/my-cases")
    public List<CaseResponse> getMyCases() {
        // 1. Spring Security Context'ten o an login olmuş kişinin emailini al
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // 2. Email'den Client profilini bul
        Client client = clientService.getClientByEmail(email);

        // 3. Sadece o client'ın davalarını dön
        // (Bunu CaseService üzerinden yapmak daha şık olurdu ama pratiklik adına burası da çalışır)
        if (client.getCases() == null) {
            return List.of();
        }
        return client.getCases().stream()
                .map(CaseMapper::toResponse)
                .toList();
    }

    @PutMapping("/{caseNumber}")
    public ResponseEntity<CaseResponse> updateCase(
            @PathVariable String caseNumber,
            @RequestBody CaseCreateRequest request) { // Güncelleme için aynı DTO'yu kullandım
        return ResponseEntity.ok(caseService.update(caseNumber, request));
    }

    @DeleteMapping("/{caseNumber}")
    public ResponseEntity<Void> deleteCase(@PathVariable String caseNumber) {
        caseService.delete(caseNumber);
        return ResponseEntity.noContent().build(); // 204 No Content döneriz
    }
}
