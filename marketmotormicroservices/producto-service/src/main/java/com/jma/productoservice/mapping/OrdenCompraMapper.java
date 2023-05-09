package com.jma.productoservice.mapping;

import com.jma.productoservice.api.ordencompra.OrdenCompraCommandInsert;
import com.jma.productoservice.api.ordencompra.OrdenCompraCommandUpdate;
import com.jma.productoservice.dto.EmpleadoDto;
import com.jma.productoservice.dto.OrdenCompraDto;
import com.jma.productoservice.dto.ProveedorDto;
import com.jma.productoservice.entity.OrdenCompraEntity;
import com.jma.productoservice.utils.EstadoC;
import com.jma.productoservice.utils.EstadoD;

public class OrdenCompraMapper {

    public static OrdenCompraDto mapToDto(OrdenCompraEntity ordenCompraEntity){

        OrdenCompraDto ordenCompraDto = new OrdenCompraDto();

        ordenCompraDto.setId(ordenCompraEntity.getId());
        ordenCompraDto.setFecha(ordenCompraEntity.getFecha());
        ordenCompraDto.setValorTotal(ordenCompraEntity.getValorTotal());
        ordenCompraDto.setActualizadoEn(ordenCompraEntity.getActualizadoEn());
        ordenCompraDto.setCreadoEn(ordenCompraEntity.getCreadoEn());
        ordenCompraDto.setEstado(ordenCompraEntity.isEstado());
        return ordenCompraDto;

    }

    public static OrdenCompraEntity mapToEntity(OrdenCompraDto ordenCompraDto){

        OrdenCompraEntity ordenCompraEntity = new OrdenCompraEntity();
        ordenCompraEntity.setFecha(ordenCompraDto.getFecha());
        ordenCompraEntity.setNumero(ordenCompraDto.getNumero());
        ordenCompraEntity.setValorTotal(ordenCompraDto.getValorTotal());
        ordenCompraEntity.setEstado(ordenCompraDto.isEstado());
        return ordenCompraEntity;

    }

    public static OrdenCompraDto mapFromCommandInsertToDto(OrdenCompraCommandInsert ordenCompraCommandInsert){

        ProveedorDto proveedorDto = new ProveedorDto();
        proveedorDto.setId(ordenCompraCommandInsert.getIdProveedor());

        EmpleadoDto empleadoDto = new EmpleadoDto();
        empleadoDto.setId(ordenCompraCommandInsert.getIdEmpleado());

        OrdenCompraDto ordenCompraDto = new OrdenCompraDto();
        ordenCompraDto.setNumero(ordenCompraCommandInsert.getNumero());
        ordenCompraDto.setValorTotal(ordenCompraCommandInsert.getValorTotal());
        ordenCompraDto.declararDisponibilidad(EstadoD.ACTIVO);
        ordenCompraDto.establecerConfirmacion(EstadoC.NO);
        ordenCompraDto.setEmpleado(empleadoDto);
        ordenCompraDto.setProveedor(proveedorDto);
        return ordenCompraDto;
    }

    public static OrdenCompraDto mapFromCommandUpdateToDto(OrdenCompraCommandUpdate ordenCompraCommandUpdate){

        OrdenCompraDto ordenCompraDto = new OrdenCompraDto();
        ordenCompraDto.setId(ordenCompraCommandUpdate.getId());
        ordenCompraDto.setNumero(ordenCompraCommandUpdate.getNumero());
        ordenCompraDto.setValorTotal(ordenCompraCommandUpdate.getValorTotal());
        ordenCompraDto.setFecha(ordenCompraCommandUpdate.getFecha());
        return ordenCompraDto;

    }

}
