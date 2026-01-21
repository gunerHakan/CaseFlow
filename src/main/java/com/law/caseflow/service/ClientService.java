package com.law.caseflow.service;

import com.law.caseflow.domain.entity.Client;
import com.law.caseflow.domain.entity.User; // Eklendi
import com.law.caseflow.domain.enums.Role; // Eklendi
import com.law.caseflow.dto.ClientCreateRequest;
import com.law.caseflow.dto.ClientDto;
import com.law.caseflow.repository.ClientRepository;
import com.law.caseflow.repository.UserRepository; // Eklendi
import com.law.caseflow.service.mapper.ClientMapper;
import org.springframework.security.crypto.password.PasswordEncoder; // Eklendi
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository; // Eklendi
    private final PasswordEncoder passwordEncoder; // Eklendi

    public ClientService(ClientRepository clientRepository,
                         UserRepository userRepository,
                         PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional // User ve Client aynı anda oluşmalı, hata olursa ikisi de rollback olsun
    public ClientDto create(ClientCreateRequest request) {

        // 1. Önce Email kontrolü (User tablosunda var mı?)
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Email already in use!");
        }

        // 2. User (Login) Kaydını Oluştur
        User newUser = new User();
        newUser.setEmail(request.email());
        // Geçici şifre: İleride random generate edip mail atabilirsin. Şimdilik sabit.
        newUser.setPassword(passwordEncoder.encode("123456"));
        newUser.setRole(Role.CLIENT);

        // User save etmeye gerek yok, CascadeType.ALL sayesinde Client save edilince o da edilecek
        // Ama ilişkiyi kurmak şart:

        // 3. Client (Profil) Kaydını Oluştur
        Client client = ClientMapper.toEntity(request);
        client.setUser(newUser); // Bağlantıyı kurduk!

        // 4. Kaydet (Cascade sayesinde User tablosuna da yazar)
        Client saved = clientRepository.save(client);

        return ClientMapper.toDto(saved);
    }

    public Client getEntityById(java.util.UUID id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new com.law.caseflow.exception.NotFoundException("Client profile not found for email: " + email));
    }
}