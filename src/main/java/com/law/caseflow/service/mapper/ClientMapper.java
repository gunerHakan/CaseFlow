package com.law.caseflow.service.mapper;

import com.law.caseflow.domain.entity.Client;
import com.law.caseflow.dto.ClientCreateRequest;
import com.law.caseflow.dto.ClientDto;

public class ClientMapper {

    public static Client toEntity(ClientCreateRequest request) {
        Client client = new Client();
        client.setFirstName(request.firstName());
        client.setLastName(request.lastName());
        client.setEmail(request.email());
        return client;
    }

    public static ClientDto toDto(Client entity) {
        return new ClientDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail()
        );
    }
}
