package com.jma.productoservice.producto.application.mapper;

import com.jma.productoservice.producto.domain.command.ProductoCommandInsert;
import com.jma.productoservice.producto.domain.command.ProductoCommandUpdate;
import com.jma.productoservice.producto.domain.dto.ProductoDto;
import com.jma.productoservice.producto.domain.entity.ProductoEntity;
import com.jma.productoservice.utils.EstadoD;

public class ProductoMapper {

    public static ProductoEntity mapToEntity(ProductoDto productoDto){

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setTipo(productoDto.getTipo());
        productoEntity.setMarca(productoDto.getMarca());
        productoEntity.setSerial(productoDto.getSerial());
        productoEntity.setDescripcion(productoDto.getDescripcion());
        productoEntity.setPrecio(productoDto.getPrecio());
        productoEntity.setEstado(productoDto.isEstado());
        productoEntity.setStock(productoDto.getStock());
        return productoEntity;
    }

    public static ProductoDto mapToDto(ProductoEntity productoEntity) {

        ProductoDto productoDto = new ProductoDto();
        productoDto.setId(productoEntity.getId());
        productoDto.setTipo(productoEntity.getTipo());
        productoDto.setMarca(productoEntity.getMarca());
        productoDto.setSerial(productoEntity.getSerial());
        productoDto.setPrecio(productoEntity.getPrecio());
        productoDto.setDescripcion(productoEntity.getDescripcion());
        productoDto.setActualizadoEn(productoEntity.getActualizadoEn());
        productoDto.setCreadoEn(productoEntity.getCreadoEn());
        productoDto.setEstado(productoEntity.isEstado());
        productoDto.setStock(productoEntity.getStock());
        return productoDto;
    }

    public static ProductoDto mapFromCommandInsertToDto(ProductoCommandInsert productoCommandInsert) {

        ProductoDto productoDto = new ProductoDto();
        productoDto.setTipo(productoCommandInsert.getTipo());
        productoDto.setMarca(productoCommandInsert.getMarca());
        productoDto.setSerial(productoCommandInsert.getSerial());
        productoDto.setDescripcion(productoCommandInsert.getDescripcion());
        productoDto.setPrecio(productoCommandInsert.getPrecio());
        productoDto.declararDisponibilidad(EstadoD.ACTIVO);
        productoDto.setStock((productoCommandInsert.getStock()));
        return productoDto;
    }

    public static ProductoDto mapFromCommandUpdateToDto(ProductoCommandUpdate productoCommandUpdate){

        ProductoDto productoDto = new ProductoDto();
        productoDto.setId(productoCommandUpdate.getId());
        productoDto.setTipo(productoCommandUpdate.getTipo());
        productoDto.setMarca(productoCommandUpdate.getMarca());
        productoDto.setSerial(productoCommandUpdate.getSerial());
        productoDto.setDescripcion(productoCommandUpdate.getDescripcion());
        productoDto.setPrecio(productoCommandUpdate.getPrecio());
        productoDto.setStock(productoCommandUpdate.getStock());
        return productoDto;
    }
}
