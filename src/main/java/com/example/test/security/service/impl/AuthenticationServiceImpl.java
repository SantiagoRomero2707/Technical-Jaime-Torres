package com.example.test.security.service.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import com.example.test.security.dto.response.AuthenticationResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.test.security.dto.request.AuthenticationRequest;
import com.example.test.security.dto.response.RegisterResponse;
import com.example.test.security.dto.request.RegisterRequest;
import com.example.test.security.repository.UserRepository;
import com.example.test.security.jwt.JwtService;
import org.springframework.stereotype.Service;
import com.example.test.security.entity.Role;
import com.example.test.security.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest request) {
        if(request.getRole() == null){
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.valueOf("USER"))
                    .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return RegisterResponse.builder()
                    .token(jwtToken)
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .authorities(user.getAuthorities())
                    .build();
        }
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return RegisterResponse.builder()
                .token(jwtToken)
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .authorities(user.getAuthorities())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .authorities(user.getAuthorities())
                .build();
    }

    @Transactional
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

}