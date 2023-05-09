package com.jma.productoservice.mapping;
import com.jma.productoservice.api.proveedor.ProveedorCommandInsert;
import com.jma.productoservice.api.proveedor.ProveedorCommandUpdate;
import com.jma.productoservice.dto.ProveedorDto;
import com.jma.productoservice.dto.UsuarioDto;
import com.jma.productoservice.entity.ProveedorEntity;
import com.jma.productoservice.utils.EstadoD;

public class ProveedorMapper {

    public static ProveedorEntity mapToEntity(ProveedorDto proveedorDto){

        ProveedorEntity proveedorEntity = new ProveedorEntity();
        proveedorEntity.setNombreComercial(proveedorDto.getNombreComercial());
        proveedorEntity.setRazonSocial(proveedorDto.getRazonSocial());
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
        proveedorDto.setId(proveedorEntity.getId());
        proveedorDto.setRazonSocial(proveedorEntity.getRazonSocial());
        proveedorDto.setNumeroRuc(proveedorEntity.getNumeroRuc());
        proveedorDto.setNombreComercial(proveedorEntity.getNombreComercial());
        proveedorDto.setCorreo(proveedorEntity.getCorreo());
        proveedorDto.setDireccion(proveedorEntity.getDireccion());
        proveedorDto.setDepartamento(proveedorEntity.getDepartamento());
        proveedorDto.setTelefonoProveedor(proveedorEntity.getTelefonoProveedor());
        proveedorDto.setCreadoEn(proveedorEntity.getCreadoEn());
        proveedorDto.setActualizadoEn(proveedorEntity.getActualizadoEn());
        proveedorDto.setEstado(proveedorEntity.isEstado());
        return proveedorDto;
    }

    public static ProveedorDto mapFromCommandInsertToDto(ProveedorCommandInsert proveedorCommandInsert) {

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(proveedorCommandInsert.getIdUsuario());

        ProveedorDto proveedorDto = new ProveedorDto();
        proveedorDto.setRazonSocial(proveedorCommandInsert.getRazonSocial());
        proveedorDto.setNombreComercial(proveedorCommandInsert.getNombreComercial());
        proveedorDto.setDireccion(proveedorCommandInsert.getDireccion());
        proveedorDto.setNumeroRuc(proveedorCommandInsert.getNumeroRuc());
        proveedorDto.setCorreo(proveedorCommandInsert.getCorreo());
        proveedorDto.setDepartamento(proveedorCommandInsert.getDepartamento());
        proveedorDto.setTelefonoProveedor(proveedorCommandInsert.getTelefonoProveedor());
        proveedorDto.declararDisponibilidad(EstadoD.ACTIVO);
        proveedorDto.setUsuarioDto(usuarioDto);
        return proveedorDto;
    }

    public static ProveedorDto mapFromCommandUpdateToDto(ProveedorCommandUpdate proveedorCommandUpdate){

        ProveedorDto proveedorDto = new ProveedorDto();
        proveedorDto.setId(proveedorCommandUpdate.getId());
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

