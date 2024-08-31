package com.example.ejemplo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DormitoriosRequest {

    @NotNull(message = "El piso es obligatorio")
    private Integer piso;

    @NotNull(message = "El numero es obligatorio")
    private Integer numero;

    @NotNull(message = "La descripcion es obligatoria")
    private String descripcion;

    private boolean estado;
    @NotNull(message = "El usuarioID es obligatorio")
    private Long usuarioID;




}
