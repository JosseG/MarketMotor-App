package com.jma.productoservice.service.impl;

import com.jma.productoservice.api.ProductoResponse;
import com.jma.productoservice.dto.ProductoDto;
import com.jma.productoservice.entity.ProductoEntity;
import com.jma.productoservice.mapping.ProductoMapper;
import com.jma.productoservice.repository.ProductoRepository;
import com.jma.productoservice.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public ProductoResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<ProductoEntity> productospageable = productoRepository.findAll(pageable);

        List<ProductoEntity> productos = productospageable.getContent();

        List<ProductoDto> content = productos.stream().map(ProductoMapper::mapToDto).toList();

        ProductoResponse productoResponse = new ProductoResponse();

        productoResponse.setContent(content);
        productoResponse.setPageNo(productospageable.getNumber());
        productoResponse.setPageSize(productospageable.getSize());
        productoResponse.setTotalElements(productospageable.getTotalElements());
        productoResponse.setTotalPages(productospageable.getTotalPages());
        productoResponse.setLast(productospageable.isLast());
        return productoResponse;
    }

    @Override
    public ProductoResponse obtenerPFiltradosPorDescripción(String descripcion, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<ProductoEntity> productospageable = productoRepository.findProductoEntitiesByDescripcionContaining(descripcion,pageable);

        List<ProductoEntity> productos = productospageable.getContent();

        List<ProductoDto> content = productos.stream().map(ProductoMapper::mapToDto).toList();

        ProductoResponse productoResponse = new ProductoResponse();

        productoResponse.setContent(content);
        productoResponse.setPageNo(productospageable.getNumber());
        productoResponse.setPageSize(productospageable.getSize());
        productoResponse.setTotalElements(productospageable.getTotalElements());
        productoResponse.setTotalPages(productospageable.getTotalPages());
        productoResponse.setLast(productospageable.isLast());
        return productoResponse;
    }

    @Override
    public ProductoDto guardar(ProductoDto object) {
        ProductoEntity productoEntity = productoRepository.save(ProductoMapper.mapToEntity(object));

        if(object.getId()!= null){
            productoEntity.setId(object.getId());
        }
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
        return productoRepository.findById((Long)id).map(ProductoMapper::mapToDto).orElse(new ProductoDto());
    }

    @Override
    public  ProductoDto actualizar(ProductoDto object) {
        Optional<ProductoEntity> opcional = productoRepository.findById(object.getId());
        ProductoEntity producto;

        if(opcional.isPresent()){
            producto = opcional.get();
            producto.setTipo(object.getTipo());
            producto.setMarca(object.getMarca());
            producto.setSerial(object.getSerial());
            producto.setDescripcion(object.getDescripcion());
            return ProductoMapper.mapToDto(productoRepository.save(producto));
        }

        return null;
    }



}
