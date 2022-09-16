package com.example.waasep2022sepringsecurity.service.impl;

import com.example.waasep2022sepringsecurity.entity.Role;
import com.example.waasep2022sepringsecurity.entity.User;
import com.example.waasep2022sepringsecurity.model.LoginRequest;
import com.example.waasep2022sepringsecurity.model.LoginResponse;
import com.example.waasep2022sepringsecurity.model.RefreshTokenRequest;
import com.example.waasep2022sepringsecurity.repository.ProductRepo;
import com.example.waasep2022sepringsecurity.repository.RolesRepo;
import com.example.waasep2022sepringsecurity.repository.UserRepo;
import com.example.waasep2022sepringsecurity.security.JwtHelper;
import com.example.waasep2022sepringsecurity.service.UaaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UaaServiceImpl implements UaaService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtHelper jwtHelper;
    private final UserRepo userRepo;
    private final RolesRepo rolesRepo;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            log.info("Bad Credentials");
        }

        final String accessToken = jwtHelper.generateToken(loginRequest.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(loginRequest.getEmail());
        LoginResponse loginResponse = new LoginResponse(accessToken, refreshToken);
        return loginResponse;
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtHelper.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            final String accessToken = jwtHelper.generateToken(jwtHelper.getSubject(refreshTokenRequest.getRefreshToken()));
            LoginResponse loginResponse = new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
            return loginResponse;
        }
        return new LoginResponse();
    }

    @Override
    public User register(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(rolesRepo.findById(2).get());
        user.setRoles(roles);
        return userRepo.save(user);
    }
}
