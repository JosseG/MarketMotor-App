package com.jma.productoservice.empleado.application.mapper;


import com.jma.productoservice.empleado.domain.command.EmpleadoCommandInsert;
import com.jma.productoservice.empleado.domain.command.EmpleadoCommandUpdate;
import com.jma.productoservice.empleado.domain.dto.EmpleadoDto;
import com.jma.productoservice.usuario.domain.dto.UsuarioDto;
import com.jma.productoservice.empleado.domain.entity.EmpleadoEntity;
import com.jma.productoservice.utils.EstadoD;

public class EmpleadoMapper {


    public static EmpleadoEntity mapToEntity(EmpleadoDto empleadoDto){

        EmpleadoEntity empleadoEntity = new EmpleadoEntity();
        empleadoEntity.setNombre(empleadoDto.getNombre());
        empleadoEntity.setCorreo(empleadoDto.getCorreo());
        empleadoEntity.setTelefono(empleadoDto.getTelefono());
        empleadoEntity.setApellidoMaterno(empleadoDto.getApellidoMaterno());
        empleadoEntity.setApellidoPaterno(empleadoDto.getApellidoPaterno());
        empleadoEntity.setEstado(empleadoDto.isEstado());
        return empleadoEntity;
    }

    public static EmpleadoDto mapToDto(EmpleadoEntity empleadoEntity){

        EmpleadoDto empleadoDto = new EmpleadoDto();
        empleadoDto.setId(empleadoEntity.getId());
        empleadoDto.setNombre(empleadoEntity.getNombre());
        empleadoDto.setCorreo(empleadoEntity.getCorreo());
        empleadoDto.setTelefono(empleadoEntity.getTelefono());
        empleadoDto.setApellidoMaterno(empleadoEntity.getApellidoMaterno());
        empleadoDto.setApellidoPaterno(empleadoEntity.getApellidoPaterno());
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
        empleadoDto.setApellidoPaterno(empleadoCommandInsert.getApellidoPaterno());
        empleadoDto.setApellidoMaterno(empleadoCommandInsert.getApellidoMaterno());
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
        empleadoDto.setApellidoPaterno(empleadoCommandUpdate.getApellidoPaterno());
        empleadoDto.setApellidoMaterno(empleadoCommandUpdate.getApellidoMaterno());
        empleadoDto.setCorreo(empleadoCommandUpdate.getCorreo());
        empleadoDto.setTelefono(empleadoCommandUpdate.getTelefono());
        return empleadoDto;
    }


}
