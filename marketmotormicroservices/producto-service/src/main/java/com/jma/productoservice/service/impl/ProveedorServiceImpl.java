package com.jma.productoservice.service.impl;

import com.jma.productoservice.dto.ProveedorDto;
import com.jma.productoservice.entity.ProveedorEntity;
import com.jma.productoservice.mapping.ProveedorMapper;
import com.jma.productoservice.repository.ProveedorRepository;
import com.jma.productoservice.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements ProveedorService<ProveedorDto> {

    private final ProveedorRepository proveedorRepository;

    @Autowired
    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) { this. proveedorRepository = proveedorRepository;}

    @Override
    public List<ProveedorDto> guardarTodos(List<ProveedorDto> list) {
        return proveedorRepository.saveAll(list.stream()
                        .map(ProveedorMapper::mapToEntity)
                        .collect(Collectors.toList()))
                .stream().map(ProveedorMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProveedorDto guardar(ProveedorDto object) {
        ProveedorEntity proveedorEntity = proveedorRepository.save(ProveedorMapper.mapToEntity(object));

        return ProveedorMapper.mapToDto(proveedorEntity);
    }

    @Override
    public List<ProveedorDto> obtenerTodos(){
        return proveedorRepository.findAll().stream().map(ProveedorMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public String eliminar(Object id) {
        try {
            proveedorRepository.deleteById((Long)id);
            return "Fue eliminado copn éxito";
        } catch (Exception ex){
            return "No se pudo eliminar,se encontró un error";
        }
    }

    @Override
    public ProveedorDto obtenerPorId(Object id) {
        return null;
    }

    @Override
    public  ProveedorDto actualizar(ProveedorDto object) {return null;}
}
