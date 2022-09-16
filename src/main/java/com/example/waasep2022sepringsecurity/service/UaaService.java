package com.example.waasep2022sepringsecurity.service;


import com.example.waasep2022sepringsecurity.entity.User;
import com.example.waasep2022sepringsecurity.model.LoginRequest;
import com.example.waasep2022sepringsecurity.model.LoginResponse;
import com.example.waasep2022sepringsecurity.model.RefreshTokenRequest;

public interface UaaService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);


    User register(User user);
}
