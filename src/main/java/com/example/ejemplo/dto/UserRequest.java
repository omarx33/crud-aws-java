package com.example.ejemplo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "solo debe ser max 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Email(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "El tipo documento es obligatorio")
    private String tipo_documento;

    @NotNull(message = "El número de documento es obligatorio")
    @Digits(integer = 11, fraction = 0, message = "El número de documento debe tener un máximo de 11 dígitos")
    private Integer numero_documento;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

}
