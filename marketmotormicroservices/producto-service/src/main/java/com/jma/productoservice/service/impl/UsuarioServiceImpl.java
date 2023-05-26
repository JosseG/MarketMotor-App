package com.jma.productoservice.service.impl;

import com.jma.productoservice.dto.UsuarioDto;
import com.jma.productoservice.entity.RolEntity;
import com.jma.productoservice.entity.UsuarioEntity;
import com.jma.productoservice.mapping.RolMapper;
import com.jma.productoservice.mapping.UsuarioMapper;
import com.jma.productoservice.repository.RolRepository;
import com.jma.productoservice.repository.UsuarioRepository;
import com.jma.productoservice.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService<UsuarioDto> {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }


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
        return usuariosMapeados(usuariossGuardados);
    }

    @Override
    public UsuarioDto getUsuarioByAlias(String alias) {
        UsuarioEntity usuarioEntity = usuarioRepository.findUsuarioEntityByAlias(alias).orElse(new UsuarioEntity());
        UsuarioDto usuarioDto = UsuarioMapper.mapToDto(usuarioEntity);
        usuarioDto.setRol(RolMapper.mapToDto(usuarioEntity.getRol()));
        return usuarioDto;
    }

    @Override
    public UsuarioDto guardar(UsuarioDto object) {

        RolEntity rolEntity = rolRepository.findById(object.getRol().getId()).orElse(null);

        UsuarioEntity usuarioEntityObt = UsuarioMapper.mapToEntity(object);
        if(object.getId()!= null){
            usuarioEntityObt.setId(object.getId());
        }

        usuarioEntityObt.setRol(rolEntity);

        UsuarioEntity usuarioEntitySaved = usuarioRepository.save(usuarioEntityObt);

        return UsuarioMapper.mapToDto(usuarioEntitySaved);
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
}
