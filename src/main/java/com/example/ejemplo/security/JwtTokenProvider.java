package com.example.ejemplo.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}") //recuperando de properties y asignando a jwtSecret
    private String jwtSecret;

    public final long JWT_TOKEN_VALIDITY = 5*60*60*1000; //5 horas. Esto se cuenta em milisegundos

    public String generateToken(Authentication authentication) { //Authentication es de securiry
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        // Suponiendo que UserPrincipal tiene métodos para obtener nombre, apellido, etc
        //armamos el token con los datos que queremos
        String nombre = userPrincipal.getUsername();
        String apellido = userPrincipal.getApellido();
        String numero_documento = userPrincipal.getNumero_documento();

        return Jwts.builder()
                .setSubject(userPrincipal.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(new SecretKeySpec(Base64.getDecoder().decode(jwtSecret), SignatureAlgorithm.HS512.getJcaName())) //firma, el algoritmo es HS512

                .claim("firstName", nombre) //agregando datos al token (no es obligatorio)
                .claim("lastName", apellido)
                .claim("dni", numero_documento)
                .claim("roles", userPrincipal.getAuthorities())
                .compact();
    }
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            // Log y manejo de excepción de firma JWT incorrecta
        } catch (MalformedJwtException ex) {
            // Log y manejo de JWT mal formado
        } catch (ExpiredJwtException ex) {
            // Log y manejo de JWT expirado
        } catch (UnsupportedJwtException ex) {
            // Log y manejo de JWT no soportado
        } catch (IllegalArgumentException ex) {
            // Log y manejo de cadena JWT vacía
        }
        //cualquier catch anteriores retornara false
        return false;
    }

    public String getEmailFromJWT(String token) { //recuperar el email del token para saber si existe en la bd
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

}
