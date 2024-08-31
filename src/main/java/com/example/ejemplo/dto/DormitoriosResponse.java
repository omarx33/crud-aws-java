package com.example.ejemplo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DormitoriosResponse {

    private Long id;

    private Integer piso;

    private Integer numero;

    private String descripcion;

    private boolean estado;

    private Long usuarioID;

}
