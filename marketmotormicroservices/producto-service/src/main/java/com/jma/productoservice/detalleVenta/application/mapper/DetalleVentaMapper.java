package com.jma.productoservice.detalleVenta.application.mapper;

import com.jma.productoservice.detalleVenta.domain.command.DetalleVentaCommandInsert;
import com.jma.productoservice.detalleVenta.domain.command.DetalleVentaCommandUpdate;
import com.jma.productoservice.detalleVenta.domain.dto.DetalleVentaDto;
import com.jma.productoservice.producto.domain.dto.ProductoDto;
import com.jma.productoservice.venta.domain.dto.VentaDto;
import com.jma.productoservice.detalleVenta.domain.entity.DetalleVentaEntity;
import com.jma.productoservice.utils.EstadoD;

public class DetalleVentaMapper {

    public static DetalleVentaDto mapToDto(DetalleVentaEntity detalleVentaEntity){

        DetalleVentaDto detalleVentaDto = new DetalleVentaDto();

        detalleVentaDto.setId(detalleVentaEntity.getId());
        detalleVentaDto.setUnidades(detalleVentaEntity.getUnidades());
        detalleVentaDto.setActualizadoEn(detalleVentaEntity.getActualizadoEn());
        detalleVentaDto.setCreadoEn(detalleVentaEntity.getCreadoEn());
        detalleVentaDto.setEstado(detalleVentaEntity.isEstado());
        return detalleVentaDto;

    }

    public static DetalleVentaEntity mapToEntity(DetalleVentaDto detalleVentaDto){

        DetalleVentaEntity detalleVentaEntity = new DetalleVentaEntity();
        detalleVentaEntity.setUnidades(detalleVentaDto.getUnidades());
        detalleVentaEntity.setEstado(detalleVentaDto.isEstado());
        return detalleVentaEntity;

    }

    public static DetalleVentaDto mapFromCommandInsertToDto(DetalleVentaCommandInsert detalleVentaCommandInsert){

        ProductoDto productoDto = new ProductoDto();
        productoDto.setId(detalleVentaCommandInsert.getIdProducto());

        VentaDto ventaDto = new VentaDto();
        ventaDto.setId(detalleVentaCommandInsert.getIdVenta());

        DetalleVentaDto detalleVentaDto = new DetalleVentaDto();
        detalleVentaDto.setUnidades(detalleVentaCommandInsert.getUnidades());
        detalleVentaDto.declararDisponibilidad(EstadoD.ACTIVO);
        detalleVentaDto.setProducto(productoDto);
        detalleVentaDto.setVenta(ventaDto);
        return detalleVentaDto;
    }

    public static DetalleVentaDto mapFromCommandUpdateToDto(DetalleVentaCommandUpdate detalleVentaCommandUpdate){

        DetalleVentaDto detalleVentaDto = new DetalleVentaDto();
        detalleVentaDto.setId(detalleVentaCommandUpdate.getId());
        return detalleVentaDto;

    }

}
