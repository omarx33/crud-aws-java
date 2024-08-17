package com.example.ejemplo.service;

import com.example.ejemplo.dto.UserRequest;
import com.example.ejemplo.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);
    UserResponse updateUser(Long id, UserRequest request);
    UserResponse getUserById(Long id);

    void deleteUser(Long id);
    List<UserResponse> getAllUsers();
}
