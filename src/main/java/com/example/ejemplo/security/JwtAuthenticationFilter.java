package com.example.ejemplo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
//minuto 1:48
@Component //debe ser gestionada por srings
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String jwt = getJwtFromRequest(request); //obtiene el token del request
        if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt) ) { //hasText si existe y es valido
            String email = jwtTokenProvider.getEmailFromJWT(jwt); //toma el correo
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication); //recupera los datos de usuarios ya autenticado
    //minuto 2:11

        }
        filterChain.doFilter(request, response);


    } //OncePerRequestFilter para que el filtro se ejecute una vez por peticion


    private String getJwtFromRequest(HttpServletRequest request) { //recibe el request con el token y retorna el token limbiado
        String bearerToken = request.getHeader("Authorization"); //Authorization es la peticion
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // recupera los datos despues de la 7ma posisión "Bearer " contando el espacio blamco
        }
        return null;
    }


}
