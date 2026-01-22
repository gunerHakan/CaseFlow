package com.law.caseflow.config;

import com.law.caseflow.domain.entity.User;
import com.law.caseflow.domain.enums.Role;
import com.law.caseflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {

            log.info(">>> DataInitializer ran! <<<");

            // 1. Admin User
            if (userRepository.findByEmail("admin@law.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@law.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                log.info(">>> Admin user created: admin@law.com");
            }

            // 2. Lawyer User
            if (userRepository.findByEmail("lawyer@test.com").isEmpty()) {
                User lawyer = new User();
                lawyer.setEmail("lawyer@test.com");
                lawyer.setPassword(passwordEncoder.encode("123456"));
                lawyer.setRole(Role.LAWYER);
                userRepository.save(lawyer);
                log.info(">>> Lawyer user created: lawyer@test.com");
            }
        };
    }
}
