package com.jma.productoservice.service.impl;

import com.jma.productoservice.dto.UsuarioDto;
import com.jma.productoservice.entity.UsuarioEntity;
import com.jma.productoservice.mapping.UsuarioMapper;
import com.jma.productoservice.repository.UsuarioRepository;
import com.jma.productoservice.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService<UsuarioDto> {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public List<UsuarioDto> guardarTodos(List<UsuarioDto> list) {
        return usuarioRepository.saveAll(list.stream()
                .map(UsuarioMapper::mapToEntity)
                .collect(Collectors.toList()))
                .stream().map(UsuarioMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDto guardar(UsuarioDto object) {

        UsuarioEntity usuarioEntity = usuarioRepository.save(UsuarioMapper.mapToEntity(object));

        return UsuarioMapper.mapToDto(usuarioEntity);
    }

    @Override
    public List<UsuarioDto> obtenerTodos() {
        return usuarioRepository.findAll().stream().map(UsuarioMapper::mapToDto).collect(Collectors.toList());
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
        return usuarioRepository.findById((Long)id).map(UsuarioMapper::mapToDto).orElse(new UsuarioDto());
    }

    @Override
    public UsuarioDto actualizar(UsuarioDto object) {
        return null;
    }
}
