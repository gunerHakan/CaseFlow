package com.law.caseflow.controller;

import com.law.caseflow.dto.ClientCreateRequest;
import com.law.caseflow.dto.ClientDto;
import com.law.caseflow.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ClientDto create(@Valid @RequestBody ClientCreateRequest request) {
        return clientService.create(request);
    }
}
