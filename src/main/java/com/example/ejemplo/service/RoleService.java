package com.example.ejemplo.service;

import com.example.ejemplo.dto.RoleResponse;
import com.example.ejemplo.model.ERole;

public interface RoleService {

    RoleResponse getRoleByName(ERole name);

}
