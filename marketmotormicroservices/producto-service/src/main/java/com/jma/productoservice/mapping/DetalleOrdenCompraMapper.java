package com.jma.productoservice.mapping;

import com.jma.productoservice.api.ordencompra.DetalleOrdenCompraCommandInsert;
import com.jma.productoservice.api.ordencompra.DetalleOrdenCompraCommandUpdate;
import com.jma.productoservice.dto.DetalleOrdenCompraDto;
import com.jma.productoservice.dto.OrdenCompraDto;
import com.jma.productoservice.dto.ProductoDto;
import com.jma.productoservice.entity.DetalleOrdenCompraEntity;
import com.jma.productoservice.utils.EstadoD;

public class DetalleOrdenCompraMapper {

    public static DetalleOrdenCompraDto mapToDto(DetalleOrdenCompraEntity detalleOrdenCompraEntity){

        DetalleOrdenCompraDto detalleOrdenCompraDto = new DetalleOrdenCompraDto();

        detalleOrdenCompraDto.setId(detalleOrdenCompraEntity.getId());
        detalleOrdenCompraDto.setCantidad(detalleOrdenCompraEntity.getCantidad());
        detalleOrdenCompraDto.setPrecioUnitario(detalleOrdenCompraEntity.getPrecioUnitario());
        detalleOrdenCompraDto.setActualizadoEn(detalleOrdenCompraEntity.getActualizadoEn());
        detalleOrdenCompraDto.setCreadoEn(detalleOrdenCompraEntity.getCreadoEn());
        detalleOrdenCompraDto.setEstado(detalleOrdenCompraEntity.isEstado());
        return detalleOrdenCompraDto;

    }

    public static DetalleOrdenCompraEntity mapToEntity(DetalleOrdenCompraDto detalleOrdenCompraDto){

        DetalleOrdenCompraEntity detalleOrdenCompraEntity = new DetalleOrdenCompraEntity();
        detalleOrdenCompraEntity.setCantidad(detalleOrdenCompraDto.getCantidad());
        detalleOrdenCompraEntity.setPrecioUnitario(detalleOrdenCompraDto.getPrecioUnitario());
        detalleOrdenCompraEntity.setEstado(detalleOrdenCompraDto.isEstado());
        return detalleOrdenCompraEntity;

    }

    public static DetalleOrdenCompraDto mapFromCommandInsertToDto(DetalleOrdenCompraCommandInsert detalleOrdenCompraCommandInsert){

        ProductoDto productoDto = new ProductoDto();
        productoDto.setId(detalleOrdenCompraCommandInsert.getIdProducto());

        OrdenCompraDto ordenCompraDto = new OrdenCompraDto();
        ordenCompraDto.setId(detalleOrdenCompraCommandInsert.getIdOrdenCompra());

        DetalleOrdenCompraDto detalleOrdenCompraDto = new DetalleOrdenCompraDto();
        detalleOrdenCompraDto.setCantidad(detalleOrdenCompraCommandInsert.getCantidad());
        detalleOrdenCompraDto.setPrecioUnitario(detalleOrdenCompraCommandInsert.getPrecioUnitario());
        detalleOrdenCompraDto.declararDisponibilidad(EstadoD.ACTIVO);
        detalleOrdenCompraDto.setProducto(productoDto);
        detalleOrdenCompraDto.setOrdenCompra(ordenCompraDto);
        return detalleOrdenCompraDto;
    }

    public static DetalleOrdenCompraDto mapFromCommandUpdateToDto(DetalleOrdenCompraCommandUpdate detalleOrdenCompraCommandUpdate){

        DetalleOrdenCompraDto detalleOrdenCompraDto = new DetalleOrdenCompraDto();
        detalleOrdenCompraDto.setId(detalleOrdenCompraCommandUpdate.getId());
        detalleOrdenCompraDto.setCantidad(detalleOrdenCompraCommandUpdate.getCantidad());
        detalleOrdenCompraDto.setPrecioUnitario(detalleOrdenCompraCommandUpdate.getPrecioUnitario());
        return detalleOrdenCompraDto;

    }

}
