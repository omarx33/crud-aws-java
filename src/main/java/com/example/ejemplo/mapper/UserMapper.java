package com.example.ejemplo.mapper;


import com.example.ejemplo.dto.UserRequest;
import com.example.ejemplo.dto.UserResponse;
import com.example.ejemplo.model.Usuarios;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper){ //constructor
        this.modelMapper=modelMapper;
    }

    public Usuarios userResquestToUsuarios(UserRequest request){
        return modelMapper.map( request, Usuarios.class );

    }

    public UserResponse usuariosToUserResponse(Usuarios usuarios){
        return modelMapper.map(usuarios, UserResponse.class);
    }



}
