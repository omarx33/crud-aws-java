package com.example.ejemplo.repository;

import com.example.ejemplo.model.Dormitorios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DormitoriosRepository extends JpaRepository<Dormitorios, Long>{

    //metodo para buscar por piso y numero
    Optional<Dormitorios> findByPisoAndNumero(Integer piso, Integer numero);
    boolean existsByPisoAndNumero(Integer piso, Integer numero);


}
