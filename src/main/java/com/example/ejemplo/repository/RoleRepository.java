package com.example.ejemplo.repository;

import com.example.ejemplo.model.ERole;
import com.example.ejemplo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
