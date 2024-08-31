package com.example.ejemplo.mapper;

import com.example.ejemplo.dto.RoleRequest;
import com.example.ejemplo.dto.RoleResponse;
import com.example.ejemplo.model.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    private final ModelMapper modelMapper;
    public RoleMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public Role roleRequestToRole(RoleRequest request){
        return modelMapper.map(request, Role.class);
    }

    public RoleResponse roleToRoleResponse(Role role){
        return modelMapper.map(role, RoleResponse.class);
    }

}
