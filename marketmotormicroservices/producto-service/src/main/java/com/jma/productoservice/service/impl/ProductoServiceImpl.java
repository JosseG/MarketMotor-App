package com.jma.productoservice.service.impl;

import com.jma.productoservice.dto.ProductoDto;
import com.jma.productoservice.entity.ProductoEntity;
import com.jma.productoservice.mapping.ProductoMapper;
import com.jma.productoservice.repository.ProductoRepository;
import com.jma.productoservice.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService<ProductoDto> {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) { this. productoRepository = productoRepository;}

    @Override
    public List<ProductoDto> guardarTodos(List<ProductoDto> list) {
        return productoRepository.saveAll(list.stream()
                .map(ProductoMapper::mapToEntity)
                .collect(Collectors.toList()))
                .stream().map(ProductoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDto guardar(ProductoDto object) {
        ProductoEntity productoEntity = productoRepository.save(ProductoMapper.mapToEntity(object));

        return ProductoMapper.mapToDto(productoEntity);
    }

    @Override
    public List<ProductoDto> obtenerTodos(){
        return productoRepository.findAll().stream().map(ProductoMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public String eliminar(Object id) {
        try {
            productoRepository.deleteById((Long)id);
            return "Fue eliminado copn éxito";
        } catch (Exception ex){
            return "No se pudo eliminar,se encontró un error";
        }
    }

    @Override
    public ProductoDto obtenerPorId(Object id) {
        return null;
    }

    @Override
    public  ProductoDto actualizar(ProductoDto object) {return null;}
}
