package com.example.ejemplo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;


    private String nombre;


    private String apellido;


    private String email;


    private String tipo_documento;


    private Integer numero_documento;


    private String password;


    private Boolean estado;
}
