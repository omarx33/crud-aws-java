package com.example.ejemplo.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse { //esto es un dto

    private String accessToken; //este token tbm puede tener el email rol u otro dato y asi evitar los de abajo
    private String tokenType = "Bearer";
    private Long userId;
    private String email;
    private List<String> roles;

}
