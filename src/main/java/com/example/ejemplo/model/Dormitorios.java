package com.example.ejemplo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Dormitorios")
public class Dormitorios {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer piso;

    private Integer numero;

    @Column( name = "descripcion", nullable = false, length = 150)
    private String descripcion;

    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_dormitorios_usuarios"))
    private Usuarios usuario;
}
