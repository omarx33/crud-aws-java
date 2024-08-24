package com.example.ejemplo.dto;

import com.example.ejemplo.model.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleResponse { //retornar role

    private Long id;
    private ERole name; //
}
