package com.jma.productoservice.service.userdetails;

import com.jma.productoservice.dto.UsuarioDto;
import com.jma.productoservice.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioService<UsuarioDto> usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuarioDto usuario= usuarioService.getUsuarioByAlias(username);
        if(usuario==null){
            return null;
        }
        GrantedAuthority rol = new SimpleGrantedAuthority(usuario.getRol().getNombre());
        List<GrantedAuthority> listaRoles = List.of(rol);

        return new User(usuario.getAlias(), usuario.getContrasena(), listaRoles);
    }

}
