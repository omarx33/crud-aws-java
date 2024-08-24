package com.example.ejemplo.security;

import com.example.ejemplo.model.Usuarios;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal implements UserDetails { //construir a partir de las entidades Role, Usuario

    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities; //GrantedAuthority son los roles

    private String nombre;
    private String apellido;
    private String tipo_documento;
    private String numero_documento;

    public static UserPrincipal create(Usuarios usuario){
    //TODO: convertir los list roles a list GrantedAuthority
      List<GrantedAuthority>  authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());


      return new UserPrincipal(
              usuario.getId(),
              usuario.getEmail(),
              usuario.getPassword(),
              authorities, //roles
              usuario.getNombre(),
              usuario.getApellido(),
              usuario.getTipo_documento(),
              usuario.getNumero_documento().toString()
      );
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //no expire la cuenta
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //no esta bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //no esta expirada
    }

    @Override
    public boolean isEnabled() {
        return true; // el usuario se genere activo
    }
}
