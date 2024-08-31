package com.example.ejemplo.api;

import com.example.ejemplo.dto.LoginRequest;
import com.example.ejemplo.dto.SignUpRequest;
import com.example.ejemplo.dto.UserInfoResponse;
import com.example.ejemplo.security.JwtAuthenticationResponse;
import com.example.ejemplo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin") //login
    public ResponseEntity<JwtAuthenticationResponse> autenticarUsuario(@RequestBody LoginRequest request) {

        JwtAuthenticationResponse jwtAuthenticationResponse = authService.authenticateUser(request);

        return new ResponseEntity<>(jwtAuthenticationResponse, HttpStatus.OK);
    }


    @PostMapping("/signup") //crear usuario
    public ResponseEntity<UserInfoResponse> registrarUsuario(@RequestBody SignUpRequest request) {

        UserInfoResponse userInfoResponse = authService.registerUser(request);

        return new ResponseEntity<>(userInfoResponse, HttpStatus.CREATED);
    }

}
