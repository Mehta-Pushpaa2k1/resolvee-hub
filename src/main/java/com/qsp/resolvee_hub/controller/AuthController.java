package com.qsp.resolvee_hub.controller;

import com.qsp.resolvee_hub.dto.AuthResponse;
import com.qsp.resolvee_hub.dto.LoginRequest;
import com.qsp.resolvee_hub.dto.RegisterRequest;
import com.qsp.resolvee_hub.entity.User;
import com.qsp.resolvee_hub.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(userService.register(request));
    }

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(userService.login(request));
    }
}
