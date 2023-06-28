package com.jma.productoservice.usuario.application.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jma.productoservice.rol.infrastructure.out.RolRepository;
import com.jma.productoservice.token.infrastructure.out.TokenRepository;
import com.jma.productoservice.usuario.domain.command.UsuarioCommandLogin;
import com.jma.productoservice.usuario.domain.dto.UsuarioDto;
import com.jma.productoservice.rol.domain.entity.RolEntity;
import com.jma.productoservice.token.domain.entity.TokenEntity;
import com.jma.productoservice.usuario.domain.entity.UsuarioEntity;
import com.jma.productoservice.rol.application.mapper.RolMapper;
import com.jma.productoservice.usuario.application.mapper.UsuarioMapper;
import com.jma.productoservice.usuario.domain.response.UserAuthenticateResponse;
import com.jma.productoservice.security.jwt.JwtService;
import com.jma.productoservice.usuario.application.service.UsuarioService;
import com.jma.productoservice.usuario.infrastructure.out.UsuarioRepository;
import com.jma.productoservice.utils.EstadoD;
import com.jma.productoservice.utils.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService<UsuarioDto> {


    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Override
    public List<UsuarioDto> guardarTodos(List<UsuarioDto> list) {
        List<RolEntity> rolesEntities = list.stream().map( e -> rolRepository.findById(e.getRol().getId()).orElse(null)).toList();
        List<UsuarioEntity> usuarioEntities = list.stream().map(UsuarioMapper::mapToEntity).toList();

        for (int i = 0; i < usuarioEntities.size(); i++) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
            //El String que mandamos al metodo encode es el password que queremos encriptar.
            System.out.println("Codificando -> "+bCryptPasswordEncoder.encode(usuarioEntities.get(i).getContrasena()));
            usuarioEntities.get(i).setContrasena(bCryptPasswordEncoder.encode(usuarioEntities.get(i).getContrasena()));
            usuarioEntities.get(i).setRol(rolesEntities.get(i));
        }
        List<UsuarioEntity> usuariossGuardados = usuarioRepository.saveAll(usuarioEntities);

        for (UsuarioEntity usuario:usuariossGuardados){
            User usuarioParsed =  mapToUser(usuario);



            var jwtToken = jwtService.generateToken(usuarioParsed);
            saveUserToken(usuario, jwtToken);
        }


        return usuariosMapeados(usuariossGuardados);
    }

    @Override
    public UsuarioDto getUsuarioByAlias(String alias) {
        UsuarioEntity usuarioEntity = usuarioRepository.findUsuarioEntityByAliasAndEstado(alias,true).orElse(new UsuarioEntity());
        UsuarioDto usuarioDto = UsuarioMapper.mapToDto(usuarioEntity);
        usuarioDto.setRol(RolMapper.mapToDto(usuarioEntity.getRol()));
        return usuarioDto;
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, UnexpectedException.class})
    public UsuarioDto guardar(UsuarioDto object) {

        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
            RolEntity rolEntity = rolRepository.findById(object.getRol().getId()).orElse(null);

            UsuarioEntity usuarioEntityObt = UsuarioMapper.mapToEntity(object);
            if (object.getId() != null) {
                usuarioEntityObt.setId(object.getId());
            }

            usuarioEntityObt.setRol(rolEntity);
            usuarioEntityObt.setContrasena(bCryptPasswordEncoder.encode(usuarioEntityObt.getContrasena()));

            UsuarioEntity usuarioEntitySaved = usuarioRepository.save(usuarioEntityObt);

            User usuarioParsed =  mapToUser(usuarioEntitySaved);

            var jwtToken = jwtService.generateToken(usuarioParsed);
            saveUserToken(usuarioEntitySaved, jwtToken);

            return UsuarioMapper.mapToDto(usuarioEntitySaved);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<UsuarioDto> obtenerTodos() {

        List<UsuarioEntity> usuarioEntities = usuarioRepository.findAll();

        return usuariosMapeados(usuarioEntities);
    }

    @Override
    public String eliminar(Object id) {
        try{
            usuarioRepository.deleteById((Long)id);
            return "Fue eliminado con éxito";
        }catch (Exception ex){
            return "No se pudo eliminar, se encontró un error";
        }
    }

    @Override
    public UsuarioDto obtenerPorId(Object id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById((Long)id).orElse(new UsuarioEntity());
        UsuarioDto usuarioDto = UsuarioMapper.mapToDto(usuarioEntity);
        usuarioDto.setRol(RolMapper.mapToDto(usuarioEntity.getRol()));

        return usuarioDto;
    }

    @Override
    public UsuarioDto cambiarEstadoActivo(UsuarioDto object){
        Optional<UsuarioEntity> opcional = usuarioRepository.findById(object.getId());
        UsuarioEntity usuario;

        if(opcional.isPresent()){
            usuario = opcional.get();
            usuario.setEstado(true);
            return UsuarioMapper.mapToDto(usuarioRepository.save(usuario));
        }

        return null;
    }

    @Override
    public UsuarioDto cambiarEstadoInactivo(UsuarioDto object) {
        Optional<UsuarioEntity> opcional = usuarioRepository.findById(object.getId());
        UsuarioEntity usuario;

        if(opcional.isPresent()){
            usuario = opcional.get();
            usuario.setEstado(false);
            return UsuarioMapper.mapToDto(usuarioRepository.save(usuario));
        }
        return null;
    }

    @Override
    public UsuarioDto actualizar(UsuarioDto object) {
        Optional<UsuarioEntity> opcional = usuarioRepository.findById(object.getId());
        UsuarioEntity usuario;

        if(opcional.isPresent()){
            usuario = opcional.get();
            usuario.setAlias(object.getAlias());
            usuario.setContrasena(object.getContrasena());
            return UsuarioMapper.mapToDto(usuarioRepository.save(usuario));
        }

        return null;
    }

    private List<UsuarioDto> usuariosMapeados(List<UsuarioEntity> usuarios) {
        List<UsuarioDto> content = usuarios.stream().map(UsuarioMapper::mapToDto).toList();

        for (int i = 0; i < content.size(); i++) {
            content.get(i).setRol(RolMapper.mapToDto(usuarios.get(i).getRol()));
        }
        return content;
    }

    @Override
    public UserAuthenticateResponse authenticate(UsuarioCommandLogin request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getAlias(),
                        request.getContrasena()
                )
        );
        UsuarioEntity usuarioEntity = usuarioRepository.findUsuarioEntityByAliasAndEstado(request.getAlias(),true).orElseThrow();

        User user =  mapToUser(usuarioEntity);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(usuarioEntity);
        saveUserToken(usuarioEntity, jwtToken);
        return UserAuthenticateResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(UsuarioEntity usuario, String jwtToken) {
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUsuario(usuario);
        tokenEntity.setToken(jwtToken);
        tokenEntity.setTipoToken(TokenType.BEARER);
        tokenEntity.setExpirado(false);
        tokenEntity.setRevocado(false);

        tokenRepository.save(tokenEntity);
    }



    private void revokeAllUserTokens(UsuarioEntity usuario) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(usuario.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpirado(true);
            token.setRevocado(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            UsuarioEntity usuario= usuarioRepository.findUsuarioEntityByAliasAndEstado(userEmail,true).orElseThrow();

            GrantedAuthority rol = new SimpleGrantedAuthority(usuario.getRol().getNombre());
            List<GrantedAuthority> listaRoles = List.of(rol);

            User user2 =  new User(usuario.getAlias(), usuario.getContrasena(), listaRoles);
            if (jwtService.isTokenValid(refreshToken, user2)) {
                var accessToken = jwtService.generateToken(user2);
                revokeAllUserTokens(usuario);
                saveUserToken(usuario, accessToken);
                var authResponse = UserAuthenticateResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public User mapToUser(UsuarioEntity usuarioEntity){
        GrantedAuthority rol = new SimpleGrantedAuthority(usuarioEntity.getRol().getNombre());

        List<GrantedAuthority> listaRoles = List.of(rol);

        for(GrantedAuthority g: listaRoles){
            System.out.println("Es es la autoridad " + g.getAuthority());
        }

        return new User(usuarioEntity.getAlias(), usuarioEntity.getContrasena(), listaRoles);
    }












}
