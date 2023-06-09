package com.jma.productoservice.security;

import com.jma.productoservice.usuario.domain.entity.UsuarioEntity;
import com.jma.productoservice.usuario.infrastructure.out.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final UsuarioRepository repository;


  /*private final UsuarioService<UsuarioDto> usuarioService;*/
  @Bean
  public UserDetailsService userDetailsService() {
    return username -> {
      UsuarioEntity usuario= repository.findUsuarioEntityByAlias(username).orElseThrow();

      GrantedAuthority rol = new SimpleGrantedAuthority(usuario.getRol().getNombre());
      List<GrantedAuthority> listaRoles = List.of(rol);

      return new User(usuario.getAlias(), usuario.getContrasena(), listaRoles);
    };
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
