package com.example.waasep2022sepringsecurity.controller;

import com.example.waasep2022sepringsecurity.entity.User;
import com.example.waasep2022sepringsecurity.model.LoginRequest;
import com.example.waasep2022sepringsecurity.model.LoginResponse;
import com.example.waasep2022sepringsecurity.model.RefreshTokenRequest;
import com.example.waasep2022sepringsecurity.service.UaaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uaa")
@CrossOrigin
public class UaaController {

    private final UaaService uaaService;

    public UaaController(UaaService uaaService) {
        this.uaaService = uaaService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = uaaService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User user1 = uaaService.register(user);
        return ResponseEntity.ok().body(user1);
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return uaaService.refreshToken(refreshTokenRequest);
    }

}
