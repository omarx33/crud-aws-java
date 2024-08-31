package com.example.ejemplo.mapper;

import com.example.ejemplo.dto.SignUpRequest;
import com.example.ejemplo.dto.UserInfoResponse;
import com.example.ejemplo.model.Usuarios;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthMapper {

    private final ModelMapper modelMapper;

    public AuthMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }


    private void configureSignUpRequestToUserTypeMap(){
        TypeMap<SignUpRequest, Usuarios> signUpRequestUsuariosTypeMap = modelMapper.
                createTypeMap(SignUpRequest.class, Usuarios.class);
        signUpRequestUsuariosTypeMap.addMappings(mapper -> {

            //el skip es deshabilitar asignacion automatica de modelomapper
                mapper.skip(Usuarios::setId); //id es automatico (no lo tome en cuenta)
                mapper.map(SignUpRequest::getEmail, Usuarios::setEmail);
                mapper.map(SignUpRequest::getPassword, Usuarios::setPassword);

//                mapper.skip(Usuarios::setEstado);
//                mapper.skip(Usuarios::setNombre);
//                mapper.skip(Usuarios::setApellido);
//                mapper.skip(Usuarios::setTipo_documento);
//                mapper.skip(Usuarios::setNumero_documento);

                mapper.skip(Usuarios::setRoles); //roles se asignan despues
    //si da error aserlo manual 1 x1 minuto 1:27
                });
    }

    public Usuarios signUpRequestToUser(SignUpRequest request){
    Usuarios usuario = modelMapper.map(request, Usuarios.class);
// aqui se mapeaaba con userprofile del video, como no tengo lo asigno todo de 1
    return usuario;

    }

    public UserInfoResponse userToUserInfoResponse(Usuarios usuario){
        UserInfoResponse response = modelMapper.map(usuario, UserInfoResponse.class);

        //mapear roles
        List<String> roles = usuario.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());
        response.setRoles(roles);
        return response;
    }

    public Usuarios loginRequestToUser(SignUpRequest request){

        //limipar datos que no se necesitan para el login

       return modelMapper.map(request, Usuarios.class);



    }


}
