package com.jma.productoservice.venta.application.mapper;

import com.jma.productoservice.cliente.domain.dto.ClienteDto;
import com.jma.productoservice.empleado.domain.dto.EmpleadoDto;
import com.jma.productoservice.venta.domain.command.VentaCommandInsert;
import com.jma.productoservice.venta.domain.command.VentaCommandUpdate;
import com.jma.productoservice.venta.domain.dto.VentaDto;
import com.jma.productoservice.venta.domain.entity.VentaEntity;
import com.jma.productoservice.utils.EstadoD;

public class VentaMapper {

    public static VentaDto mapToDto(VentaEntity ventaEntity){

        VentaDto ventaDto = new VentaDto();

        ventaDto.setId(ventaEntity.getId());
        ventaDto.setPreciototal(ventaEntity.getPreciototal());
        ventaDto.setActualizadoEn(ventaEntity.getActualizadoEn());
        ventaDto.setCreadoEn(ventaEntity.getCreadoEn());
        ventaDto.setEstado(ventaEntity.isEstado());
        return ventaDto;

    }

    public static VentaEntity mapToEntity(VentaDto ventaDto){

        VentaEntity ventaEntity = new VentaEntity();
        ventaEntity.setPreciototal(ventaDto.getPreciototal());
        ventaEntity.setEstado(ventaDto.isEstado());
        return ventaEntity;

    }

    public static VentaDto mapFromCommandInsertToDto(VentaCommandInsert ventaCommandInsert){

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(ventaCommandInsert.getIdCliente());

        EmpleadoDto empleadoDto = new EmpleadoDto();
        empleadoDto.setId(ventaCommandInsert.getIdEmpleado());

        VentaDto ventaDto = new VentaDto();
        ventaDto.setPreciototal(ventaCommandInsert.getPreciototal());
        ventaDto.declararDisponibilidad(EstadoD.ACTIVO);
        ventaDto.setEmpleado(empleadoDto);
        ventaDto.setCliente(clienteDto);
        return ventaDto;
    }

    public static VentaDto mapFromCommandUpdateToDto(VentaCommandUpdate ventaCommandUpdate){

        VentaDto ventaDto = new VentaDto();
        ventaDto.setId(ventaCommandUpdate.getId());
        ventaDto.setPreciototal(ventaCommandUpdate.getPreciototal());
        return ventaDto;

    }
    
}
