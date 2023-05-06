package com.jma.productoservice.mapping;

import com.jma.productoservice.api.rol.RolCommandInsert;
import com.jma.productoservice.api.rol.RolCommandUpdate;
import com.jma.productoservice.dto.RolDto;
import com.jma.productoservice.entity.RolEntity;
import com.jma.productoservice.utils.EstadoD;

public class RolMapper {


    public static RolDto mapToDto(RolEntity rolEntity){
        RolDto rolDto = new RolDto();
        rolDto.setId(rolEntity.getId());
        rolDto.setNombre(rolEntity.getNombre());
        rolDto.setEstado(rolEntity.isEstado());

        return rolDto;
    }

    public static RolEntity mapToEntity(RolDto rolDto){
        RolEntity rolEntity = new RolEntity();
        rolEntity.setNombre(rolDto.getNombre());
        rolEntity.setEstado(rolDto.isEstado());
        return rolEntity;
    }


    public static RolDto mapFromCommandInsertToDto(RolCommandInsert rolCommandInsert){
        RolDto rolDto = new RolDto();
        rolDto.setNombre(rolCommandInsert.getNombre());
        rolDto.declararDisponibilidad(EstadoD.ACTIVO);
        return rolDto;
    }

    public static RolDto mapFromCommandUpdateToDto(RolCommandUpdate rolCommandUpdate){
        RolDto rolDto = new RolDto();
        rolDto.setId(rolCommandUpdate.getId());
        rolDto.setNombre(rolCommandUpdate.getNombre());
        rolDto.setEstado(rolCommandUpdate.isEstado());
        return rolDto;
    }

}
