package com.jma.productoservice.mapping;

import com.jma.productoservice.api.producto.ProductoCommandInsert;
import com.jma.productoservice.api.producto.ProductoCommandUpdate;
import com.jma.productoservice.dto.ProductoDto;
import com.jma.productoservice.entity.ProductoEntity;
import com.jma.productoservice.utils.EstadoD;

public class ProductoMapper {

    public static ProductoEntity mapToEntity(ProductoDto productoDto){

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setTipo(productoDto.getTipo());
        productoEntity.setMarca(productoDto.getMarca());
        productoEntity.setSerial(productoDto.getSerial());
        productoEntity.setDescripcion(productoDto.getDescripcion());
        productoEntity.setEstado(productoDto.isEstado());
        return productoEntity;
    }

    public static ProductoDto mapToDto(ProductoEntity productoEntity) {

        ProductoDto productoDto = new ProductoDto();
        productoDto.setId(productoEntity.getId());
        productoDto.setTipo(productoEntity.getTipo());
        productoDto.setMarca(productoEntity.getMarca());
        productoDto.setSerial(productoEntity.getSerial());
        productoDto.setDescripcion(productoEntity.getDescripcion());
        productoDto.setActualizadoEn(productoEntity.getActualizadoEn());
        productoDto.setCreadoEn(productoEntity.getCreadoEn());
        productoDto.setEstado(productoEntity.isEstado());
        return productoDto;
    }

    public static ProductoDto mapFromCommandInsertToDto(ProductoCommandInsert productoCommandInsert) {

        ProductoDto productoDto = new ProductoDto();
        productoDto.setTipo(productoCommandInsert.getTipo());
        productoDto.setMarca(productoCommandInsert.getMarca());
        productoDto.setSerial(productoCommandInsert.getSerial());
        productoDto.setDescripcion(productoCommandInsert.getDescripcion());
        productoDto.declararDisponibilidad(EstadoD.ACTIVO);
        return productoDto;
    }

    public static ProductoDto mapFromCommandUpdateToDto(ProductoCommandUpdate productoCommandUpdate){

        ProductoDto productoDto = new ProductoDto();
        productoDto.setId(productoCommandUpdate.getId());
        productoDto.setTipo(productoCommandUpdate.getTipo());
        productoDto.setMarca(productoCommandUpdate.getMarca());
        productoDto.setSerial(productoCommandUpdate.getSerial());
        productoDto.setDescripcion(productoCommandUpdate.getDescripcion());
        return productoDto;
    }
}
