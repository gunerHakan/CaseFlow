package com.law.caseflow.service;

import com.law.caseflow.domain.entity.Client;
import com.law.caseflow.domain.entity.User;
import com.law.caseflow.domain.enums.Role;
import com.law.caseflow.dto.ClientCreateRequest;
import com.law.caseflow.dto.ClientDto;
import com.law.caseflow.exception.NotFoundException;
import com.law.caseflow.exception.UnauthorizedException;
import com.law.caseflow.repository.ClientRepository;
import com.law.caseflow.repository.UserRepository;
import com.law.caseflow.service.mapper.ClientMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository,
                         UserRepository userRepository,
                         PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ClientDto create(ClientCreateRequest request) {

        // 1. Login olmuş kullanıcıyı (Avukatı) bul
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String lawyerEmail = authentication.getName();
        User lawyer = userRepository.findByEmail(lawyerEmail)
                .orElseThrow(() -> new NotFoundException("Lawyer not found"));

        // 2. Email kontrolü
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Email already in use!");
        }

        // 3. User (Login) Kaydını Oluştur
        User newUser = new User();
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode("123456"));
        newUser.setRole(Role.CLIENT);

        // 4. Client (Profil) Kaydını Oluştur
        Client client = ClientMapper.toEntity(request);
        client.setUser(newUser);
        client.setLawyer(lawyer); // Müvekkili avukata bağla!

        // 5. Save
        Client saved = clientRepository.save(client);

        return ClientMapper.toDto(saved);
    }

    public Client getEntityById(java.util.UUID id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Client profile not found for email: " + email));
    }
}
