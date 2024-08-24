package com.example.ejemplo.repository;

import com.example.ejemplo.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    //buscar por numero de documentp

    @Query("SELECT u FROM Usuarios u WHERE u.numero_documento = :numero_documento")
    List<Usuarios> BuscarNumero_documento(Integer numero_documento);



    //TODO: metodo para autenticar
    Optional<Usuarios> findByEmail(String email);
    boolean existsByEmail(String email);
}
