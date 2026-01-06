package com.law.caseflow.config;

import com.law.caseflow.domain.entity.User;
import com.law.caseflow.domain.enums.Role;
import com.law.caseflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {

            System.out.println(">>> DataInitializer çalıştı");

            if (userRepository.findByEmail("lawyer@test.com").isEmpty()) {

                User user = new User();
                user.setEmail("lawyer@test.com");
                user.setPassword(passwordEncoder.encode("123456"));
                user.setRole(Role.LAWYER);

                userRepository.save(user);

                System.out.println(">>> Lawyer user oluşturuldu");
            }
        };
    }
}
