package com.law.caseflow.service;

import com.law.caseflow.domain.entity.Client;
import com.law.caseflow.dto.ClientCreateRequest;
import com.law.caseflow.dto.ClientDto;
import com.law.caseflow.repository.ClientRepository;
import com.law.caseflow.service.mapper.ClientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientDto create(ClientCreateRequest request) {
        Client client = ClientMapper.toEntity(request);
        Client saved = clientRepository.save(client);
        return ClientMapper.toDto(saved);
    }

    public Client getEntityById(java.util.UUID id) {
        return clientRepository.findById(id).orElse(null);
    }
}
