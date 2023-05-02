package com.jma.productoservice.service.impl;

import com.jma.productoservice.entity.EmpleadoEntity;
import com.jma.productoservice.entity.UsuarioEntity;
import com.jma.productoservice.repository.UsuarioRepository;
import com.jma.productoservice.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService<UsuarioEntity> {

    private final UsuarioRepository usuarioRepository;
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override




    public UsuarioEntity guardar(UsuarioEntity object) {
        return usuarioRepository.save(object);
    }

    @Override
    public List<UsuarioEntity> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public void eliminar(Object id) {

    }

    @Override
    public UsuarioEntity obtenerPorId(Object id) {

        if(usuarioRepository.findById((Long)id).isEmpty()){
            return new UsuarioEntity();
        }
        return usuarioRepository.findById((Long)id).get();

    }
}
