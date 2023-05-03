package com.jma.productoservice.mapping;


import com.jma.productoservice.api.empleado.EmpleadoCommandInsert;
import com.jma.productoservice.api.empleado.EmpleadoCommandUpdate;
import com.jma.productoservice.dto.EmpleadoDto;
import com.jma.productoservice.dto.UsuarioDto;
import com.jma.productoservice.entity.EmpleadoEntity;
import com.jma.productoservice.utils.EstadoD;

public class EmpleadoMapper {


    public static EmpleadoEntity mapToEntity(EmpleadoDto empleadoDto){

        EmpleadoEntity empleadoEntity = new EmpleadoEntity();
        empleadoEntity.setNombre(empleadoDto.getNombre());
        empleadoEntity.setCorreo(empleadoDto.getCorreo());
        empleadoEntity.setTelefono(empleadoDto.getTelefono());
        empleadoEntity.setApellidoMat(empleadoDto.getApellidoMat());
        empleadoEntity.setApellidoPat(empleadoDto.getApellidoPat());
        empleadoEntity.setEstado(empleadoDto.isEstado());
        return empleadoEntity;
    }

    public static EmpleadoDto mapToDto(EmpleadoEntity empleadoEntity){

        EmpleadoDto empleadoDto = new EmpleadoDto();
        empleadoDto.setId(empleadoEntity.getId());
        empleadoDto.setNombre(empleadoEntity.getNombre());
        empleadoDto.setCorreo(empleadoEntity.getCorreo());
        empleadoDto.setTelefono(empleadoEntity.getTelefono());
        empleadoDto.setApellidoMat(empleadoEntity.getApellidoMat());
        empleadoDto.setApellidoPat(empleadoEntity.getApellidoPat());
        empleadoDto.setActualizadoEn(empleadoEntity.getActualizadoEn());
        empleadoDto.setCreadoEn(empleadoEntity.getCreadoEn());
        empleadoDto.setEstado(empleadoEntity.isEstado());
        return empleadoDto;
    }

    public static EmpleadoDto mapFromCommandInsertToDto(EmpleadoCommandInsert empleadoCommandInsert){

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(empleadoCommandInsert.getIdUsuario());

        EmpleadoDto empleadoDto = new EmpleadoDto();
        empleadoDto.setNombre(empleadoCommandInsert.getNombre());
        empleadoDto.setApellidoPat(empleadoCommandInsert.getApellidoPat());
        empleadoDto.setApellidoMat(empleadoCommandInsert.getApellidoMat());
        empleadoDto.setCorreo(empleadoCommandInsert.getCorreo());
        empleadoDto.setTelefono(empleadoCommandInsert.getTelefono());
        empleadoDto.setUsuarioDto(usuarioDto);
        empleadoDto.declararDisponibilidad(EstadoD.ACTIVO);
        return empleadoDto;
    }


    public static EmpleadoDto mapFromCommandUpdateToDto(EmpleadoCommandUpdate empleadoCommandUpdate){


        EmpleadoDto empleadoDto = new EmpleadoDto();
        empleadoDto.setId(empleadoCommandUpdate.getId());
        empleadoDto.setNombre(empleadoCommandUpdate.getNombre());
        empleadoDto.setApellidoPat(empleadoCommandUpdate.getApellidoPat());
        empleadoDto.setApellidoMat(empleadoCommandUpdate.getApellidoMat());
        empleadoDto.setCorreo(empleadoCommandUpdate.getCorreo());
        empleadoDto.setTelefono(empleadoCommandUpdate.getTelefono());
        return empleadoDto;
    }


}
