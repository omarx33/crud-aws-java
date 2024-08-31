package com.example.ejemplo.service.impl;

import com.example.ejemplo.dto.LoginRequest;
import com.example.ejemplo.dto.RoleResponse;
import com.example.ejemplo.dto.SignUpRequest;
import com.example.ejemplo.dto.UserInfoResponse;
import com.example.ejemplo.exception.BadRequestException;
import com.example.ejemplo.mapper.AuthMapper;
import com.example.ejemplo.model.ERole;
import com.example.ejemplo.model.Role;
import com.example.ejemplo.model.Usuarios;
import com.example.ejemplo.repository.UsuarioRepository;
import com.example.ejemplo.security.JwtAuthenticationResponse;
import com.example.ejemplo.security.JwtTokenProvider;
import com.example.ejemplo.security.UserPrincipal;
import com.example.ejemplo.service.AuthService;
import com.example.ejemplo.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

//explicacione en min 2:23 video 6 todotic
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final RoleService roleService;
    private final AuthMapper authMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserInfoResponse registerUser(SignUpRequest request) {

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email ya registrado");
        }
        Usuarios usuario = authMapper.signUpRequestToUser(request);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));//codifica password

        //asignar rol
        RoleResponse roleResponse = roleService.getRoleByName(ERole.ROLE_USER);
        Role role = new Role(); //crear objeto role
        role.setId(roleResponse.getId());
        role.setName(ERole.valueOf(String.valueOf(roleResponse.getName()))); //asignar nombre de rol

        usuario.setRoles(Collections.singletonList(role)); //asignar rol a usuario

        Usuarios usuarioGuardado = usuarioRepository.save(usuario); //guardar usuario
        return authMapper.userToUserInfoResponse(usuarioGuardado);

    }

    @Override
    public JwtAuthenticationResponse authenticateUser(LoginRequest request) { //login
        //autenticar usuario
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Establecer autenticación en el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication); //guardar autenticacion

        // Generar token
        String token = jwtTokenProvider.generateToken(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal(); //obtener usuario autenticado

        // Crear y devolver JwtAuthenticationResponse con información adicional
        return new JwtAuthenticationResponse(
                token,
                "Bearer",
                userPrincipal.getId(),
                userPrincipal.getUsername(),
                userPrincipal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()
        ));


    } //registrar cuenta e inicio se sesion


}
