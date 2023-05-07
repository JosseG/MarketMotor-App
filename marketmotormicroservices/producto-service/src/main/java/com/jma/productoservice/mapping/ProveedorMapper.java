package com.jma.productoservice.mapping;

import com.jma.productoservice.api.proveedor.ProveedorCommandInsert;
import com.jma.productoservice.api.proveedor.ProveedorCommandUpdate;
import com.jma.productoservice.dto.ProveedorDto;
import com.jma.productoservice.entity.ProveedorEntity;

public class ProveedorMapper {
    public static ProveedorEntity mapToEntity(ProveedorDto proveedorDto){

        ProveedorEntity proveedorEntity = new ProveedorEntity();
        proveedorEntity.setRazonSocial(proveedorDto.getRazonSocial());
        proveedorEntity.setNombreComercial(proveedorDto.getNombreComercial());
        proveedorEntity.setNumeroRuc(proveedorDto.getNumeroRuc());
        proveedorEntity.setCorreo(proveedorDto.getCorreo());
        proveedorEntity.setDireccion(proveedorDto.getDireccion());
        proveedorEntity.setDepartamento(proveedorDto.getDepartamento());
        proveedorEntity.setTelefonoProveedor(proveedorDto.getTelefonoProveedor());
        proveedorEntity.setEstado(proveedorDto.isEstado());
        return proveedorEntity;
    }

    public static ProveedorDto mapToDto(ProveedorEntity proveedorEntity) {

        ProveedorDto proveedorDto = new ProveedorDto();
        proveedorDto.setIdProveedor(proveedorEntity.getIdProveedor());
        proveedorDto.setRazonSocial(proveedorEntity.getRazonSocial());
        proveedorDto.setNombreComercial(proveedorEntity.getNombreComercial());
        proveedorDto.setNumeroRuc(proveedorEntity.getNumeroRuc());
        proveedorDto.setCorreo(proveedorEntity.getCorreo());
        proveedorDto.setDireccion(proveedorEntity.getDireccion());
        proveedorDto.setDepartamento(proveedorEntity.getDepartamento());
        proveedorDto.setTelefonoProveedor(proveedorEntity.getTelefonoProveedor());
        proveedorDto.setEstado(proveedorEntity.isEstado());

        return proveedorDto;
    }

    public static ProveedorDto mapFromCommandInsertToDto(ProveedorCommandInsert proveedorCommandInsert) {

        ProveedorDto proveedorDto = new ProveedorDto();

        proveedorDto.setIdProveedor(proveedorCommandInsert.getIdProveedor());
        proveedorDto.setRazonSocial(proveedorCommandInsert.getRazonSocial());
        proveedorDto.setNombreComercial(proveedorCommandInsert.getNombreComercial());
        proveedorDto.setNumeroRuc(proveedorCommandInsert.getNumeroRuc());
        proveedorDto.setCorreo(proveedorCommandInsert.getCorreo());
        proveedorDto.setDireccion(proveedorCommandInsert.getDireccion());
        proveedorDto.setDepartamento(proveedorCommandInsert.getDepartamento());
        proveedorDto.setTelefonoProveedor(proveedorCommandInsert.getTelefonoProveedor());

        return proveedorDto;
    }

    public static ProveedorDto mapFromCommandUpdateToDto(ProveedorCommandUpdate proveedorCommandUpdate){

        ProveedorDto proveedorDto = new ProveedorDto();

        proveedorDto.setIdProveedor(proveedorCommandUpdate.getIdProveedor());
        proveedorDto.setRazonSocial(proveedorCommandUpdate.getRazonSocial());
        proveedorDto.setNombreComercial(proveedorCommandUpdate.getNombreComercial());
        proveedorDto.setNumeroRuc(proveedorCommandUpdate.getNumeroRuc());
        proveedorDto.setCorreo(proveedorCommandUpdate.getCorreo());
        proveedorDto.setDireccion(proveedorCommandUpdate.getDireccion());
        proveedorDto.setDepartamento(proveedorCommandUpdate.getDepartamento());
        proveedorDto.setTelefonoProveedor(proveedorCommandUpdate.getTelefonoProveedor());

        return proveedorDto;
    }
}
