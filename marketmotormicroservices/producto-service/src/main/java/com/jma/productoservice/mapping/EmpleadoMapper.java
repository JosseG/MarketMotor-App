package com.jma.productoservice.mapping;

import com.jma.productoservice.dto.EmpleadoDto;
import com.jma.productoservice.entity.EmpleadoEntity;

public class EmpleadoMapper {


    public static EmpleadoEntity mapToEntity(EmpleadoDto empleadoDto){

        EmpleadoEntity empleadoEntity = new EmpleadoEntity();
        empleadoEntity.setNombre(empleadoDto.getNombre());
        return empleadoEntity;
    }

    public static EmpleadoDto mapToDto(EmpleadoEntity empleadoEntity){

        EmpleadoDto empleadoDto = new EmpleadoDto();
        empleadoEntity.setNombre(empleadoEntity.getNombre());
        return empleadoDto;
    }






}
