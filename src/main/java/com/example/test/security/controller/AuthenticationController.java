package com.example.test.security.controller;

import com.example.test.security.service.impl.AuthenticationServiceImpl;
import com.example.test.security.dto.response.AuthenticationResponse;
import com.example.test.security.dto.request.AuthenticationRequest;
import com.example.test.security.dto.response.RegisterResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.test.security.dto.request.RegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationServiceImpl service;

    public AuthenticationController(AuthenticationServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
