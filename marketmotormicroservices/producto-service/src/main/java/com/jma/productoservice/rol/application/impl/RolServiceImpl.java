package com.jma.productoservice.rol.application.impl;

import com.jma.productoservice.rol.domain.dto.RolDto;
import com.jma.productoservice.rol.domain.entity.RolEntity;
import com.jma.productoservice.rol.application.mapper.RolMapper;
import com.jma.productoservice.rol.application.service.RolService;
import com.jma.productoservice.rol.infrastructure.out.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService<RolDto> {


    private final RolRepository rolRepository;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }


    @Override
    public RolDto guardar(RolDto object) {

        RolEntity rolEntity = RolMapper.mapToEntity(object);
        if (object.getId()!= null) {
            rolEntity.setId(object.getId());
        }

        return RolMapper.mapToDto(rolRepository.save(rolEntity));
    }

    @Override
    public RolDto actualizar(RolDto object) {

        RolEntity rolEntity = RolMapper.mapToEntity(object);
        rolEntity.setId(object.getId());

        return RolMapper.mapToDto(rolRepository.save(rolEntity));
    }

    @Override
    public List<RolDto> obtenerTodos() {

        List<RolEntity> rolEntities = rolRepository.findAll();

        return getRolDtos(rolEntities);
    }

    @Override
    public String eliminar(Object id) {
        try {
            rolRepository.deleteById((Long) id);
            return "Fue eliminado con éxito";
        } catch (Exception ex) {
            return "No se pudo eliminar, se encontró un error";
        }
    }

    @Override
    public RolDto obtenerPorId(Object id) {
        return rolRepository.findById((Long) id).map(RolMapper::mapToDto).orElse(null);
    }

    @Override
    public List<RolDto> guardarTodos(List<RolDto> list) {
        List<RolEntity> rolesEntities = list.stream().map(RolMapper::mapToEntity).toList();

        List<RolEntity> rolesGuardados = rolRepository.saveAll(rolesEntities);
        return rolesGuardados.stream().map(RolMapper::mapToDto).collect(Collectors.toList());
    }

    private List<RolDto> getRolDtos(List<RolEntity> rolEntities) {
        List<RolDto> rolDtos = rolEntities.stream().map(RolMapper::mapToDto).toList();

        return rolDtos;
    }


}
