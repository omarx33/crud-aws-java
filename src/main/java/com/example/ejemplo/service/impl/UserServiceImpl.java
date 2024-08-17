package com.example.ejemplo.service.impl;

import com.example.ejemplo.dto.UserRequest;
import com.example.ejemplo.dto.UserResponse;
import com.example.ejemplo.exception.AssignmentAlreadyExistsException;
import com.example.ejemplo.exception.ResourceNotFoundException;
import com.example.ejemplo.mapper.UserMapper;
import com.example.ejemplo.model.Usuarios;
import com.example.ejemplo.repository.UsuarioRepository;
import com.example.ejemplo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UsuarioRepository usuarioRepository;

    @Transactional // si falla retorna a su estado anterior la data
    @Override
    public UserResponse createUser(UserRequest request) {

        //validar si el usuario ya existe por numero de documento

        List<Usuarios> usuario = usuarioRepository.BuscarNumero_documento(request.getNumero_documento());
        if (!usuario.isEmpty()){
            throw new AssignmentAlreadyExistsException("Usuario ya existe con el numero de documento " + request.getNumero_documento());
        }

        Usuarios usuarios = userMapper.userResquestToUsuarios(request);
        usuarios = usuarioRepository.save(usuarios);
        return  userMapper.usuariosToUserResponse(usuarios);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {

        Usuarios usuarios = usuarioRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Usuario no encontrado " + id));
        usuarios.setNombre(request.getNombre());
        usuarios.setApellido(request.getApellido());
        usuarios.setEmail(request.getEmail());
        usuarios.setTipo_documento(request.getTipo_documento());
        usuarios.setNumero_documento(request.getNumero_documento());
        usuarios.setPassword(request.getPassword());
        usuarios.setEstado(request.getEstado());
        usuarios = usuarioRepository.save(usuarios);
        return userMapper.usuariosToUserResponse(usuarios);

    }

    @Override
    public UserResponse getUserById(Long id) {

        Usuarios usuarios = usuarioRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado " + id));
        return userMapper.usuariosToUserResponse(usuarios);

    }

    @Override
    public void deleteUser(Long id) {
        if (!usuarioRepository.existsById(id)){
            throw new ResourceNotFoundException("Usuario no encontrado" + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> getAllUsers() {
       List<Usuarios> usuarios = usuarioRepository.findAll();
       return usuarios.stream().map(userMapper::usuariosToUserResponse ).toList();
    }




}
