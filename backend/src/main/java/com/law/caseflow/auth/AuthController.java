package com.law.caseflow.auth;

import com.law.caseflow.domain.entity.User;
import com.law.caseflow.domain.enums.Role;
import com.law.caseflow.repository.UserRepository;
import com.law.caseflow.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor // Constructor injection için Lombok
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository; // EKLENDİ
    private final PasswordEncoder passwordEncoder; // EKLENDİ

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.username(),
                                request.password()
                        )
                );

        String token = jwtUtil.generateToken(authentication.getName());
        return new LoginResponse(token);
    }

    @PostMapping("/register-lawyer")
    public ResponseEntity<String> registerLawyer(@Valid @RequestBody RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            return new ResponseEntity<>("Email already in use!", HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password())); // Şifreyi encode et
        newUser.setRole(Role.LAWYER); // Rolü LAWYER olarak ayarla

        userRepository.save(newUser);
        return new ResponseEntity<>("Lawyer registered successfully!", HttpStatus.CREATED);
    }
}
