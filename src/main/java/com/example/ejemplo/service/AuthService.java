package com.example.ejemplo.service;

import com.example.ejemplo.dto.LoginRequest;
import com.example.ejemplo.dto.SignUpRequest;
import com.example.ejemplo.dto.UserInfoResponse;
import com.example.ejemplo.security.JwtAuthenticationResponse;

public interface AuthService {

    UserInfoResponse registerUser(SignUpRequest request);
    JwtAuthenticationResponse authenticateUser(LoginRequest request);

}
