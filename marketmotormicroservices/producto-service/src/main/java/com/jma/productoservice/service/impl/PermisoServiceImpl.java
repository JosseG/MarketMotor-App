package com.jma.productoservice.service.impl;

import com.jma.productoservice.dto.PermisoDto;
import com.jma.productoservice.entity.PermisoEntity;
import com.jma.productoservice.mapping.PermisoMapper;
import com.jma.productoservice.repository.PermisoRepository;
import com.jma.productoservice.service.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermisoServiceImpl implements PermisoService<PermisoDto> {

    private final PermisoRepository permisoRepository;

    @Autowired
    public PermisoServiceImpl(PermisoRepository permisoRepository){
        this.permisoRepository = permisoRepository;
    }

    @Override
    public PermisoDto guardar(PermisoDto object) {
        PermisoEntity permisoEntity = PermisoMapper.mapToEntity(object);

        if(object.getId()!= null){
            permisoEntity.setId(object.getId());
        }

        return PermisoMapper.mapToDto(permisoRepository.save(permisoEntity));
    }

    @Override
    public List<PermisoDto> obtenerTodos() {
        return permisoRepository.findAll().stream().map(PermisoMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public String eliminar(Object id) {
        try{
            permisoRepository.deleteById((Long)id);
            return "Fue eliminado con éxito";
        }catch (Exception ex){
            return "No se pudo eliminar, se encontró un error";
        }
    }

    @Override
    public PermisoDto obtenerPorId(Object id) {
        return permisoRepository.findById((Long)id).map(PermisoMapper::mapToDto).orElse(null);
    }

    @Override
    public PermisoDto actualizar(PermisoDto object) {
        PermisoEntity permisoEntity = PermisoMapper.mapToEntity(object);
        permisoEntity.setId(object.getId());

        return PermisoMapper.mapToDto(permisoRepository.save(permisoEntity));
    }

    @Override
    public List<PermisoDto> buscarPermisosPorRolId(Long rolId) {
        List<PermisoEntity> permisoEntities = permisoRepository.findPermisoEntitiesByRolesId(rolId);

        return permisoEntities.stream().map(PermisoMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<PermisoDto> guardarTodos(List<PermisoDto> list) {
        List<PermisoEntity> permisosEntities = list.stream().map(PermisoMapper::mapToEntity).toList();

        List<PermisoEntity> permisosGuardados = permisoRepository.saveAll(permisosEntities);
        return permisosGuardados.stream().map(PermisoMapper::mapToDto).collect(Collectors.toList());
    }
}
