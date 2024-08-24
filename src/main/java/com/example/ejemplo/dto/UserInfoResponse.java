package com.example.ejemplo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {

    //retorna perfil y rol
    private Long id;
    private String email;
    private String nombre;
    private String apellido;
    private String tipo_documento;
    private Integer numero_documento;

    private boolean estado;
    private List<String> roles;

}
