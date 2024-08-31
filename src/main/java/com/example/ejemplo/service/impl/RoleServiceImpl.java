package com.example.ejemplo.service.impl;

import com.example.ejemplo.dto.RoleResponse;
import com.example.ejemplo.exception.ResourceNotFoundException;
import com.example.ejemplo.mapper.RoleMapper;
import com.example.ejemplo.model.ERole;
import com.example.ejemplo.model.Role;
import com.example.ejemplo.repository.RoleRepository;
import com.example.ejemplo.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper ){
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleResponse getRoleByName(ERole name) {

        Role role = roleRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role no existe."));
        return roleMapper.roleToRoleResponse(role);
    }


}
