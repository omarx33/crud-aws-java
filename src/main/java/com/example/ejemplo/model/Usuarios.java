package com.example.ejemplo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Usuarios")
public class Usuarios {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column( name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column( name = "email", nullable = false, length = 100)
    private String email;

    @Column( name = "tipo_documento", nullable = false, length = 50)
    private String tipo_documento;

    @Column( name = "numero_documento", nullable = false)
    private Integer numero_documento;

    @Column(nullable = false)
    private String password;

    @Column
    private boolean estado;
}
