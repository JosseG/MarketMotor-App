package com.jma.productoservice.mapping;

import com.jma.productoservice.dto.EmpleadoDto;
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
        empleadoEntity.declararDisponibilidad(EstadoD.ACTIVO);
        return empleadoEntity;
    }

    public static EmpleadoDto mapToDto(EmpleadoEntity empleadoEntity){

        EmpleadoDto empleadoDto = new EmpleadoDto();
        empleadoDto.setNombre(empleadoEntity.getNombre());
        empleadoDto.setCorreo(empleadoEntity.getCorreo());
        empleadoDto.setTelefono(empleadoEntity.getTelefono());
        empleadoDto.setApellidoMat(empleadoEntity.getApellidoMat());
        empleadoDto.setApellidoPat(empleadoEntity.getApellidoPat());
        empleadoDto.setIdUsuario(empleadoEntity.getUsuario().getId());
        return empleadoDto;
    }






}
