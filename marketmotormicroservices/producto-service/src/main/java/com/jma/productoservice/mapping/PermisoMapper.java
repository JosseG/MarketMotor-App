package com.jma.productoservice.mapping;


import com.jma.productoservice.api.permiso.PermisoCommandInsert;
import com.jma.productoservice.api.permiso.PermisoCommandUpdate;
import com.jma.productoservice.dto.PermisoDto;
import com.jma.productoservice.entity.PermisoEntity;
import com.jma.productoservice.utils.EstadoD;

public class PermisoMapper {

    public static PermisoDto mapToDto(PermisoEntity permisoEntity){
        PermisoDto permisoDto = new PermisoDto();
        permisoDto.setId(permisoEntity.getId());
        permisoDto.setTipo(permisoEntity.getTipo());
        permisoDto.setEstado(permisoEntity.isEstado());
        return permisoDto;
    }

    public static PermisoEntity mapToEntity(PermisoDto permisoDto){
        PermisoEntity permisoEntity = new PermisoEntity();
        permisoEntity.setTipo(permisoDto.getTipo());
        permisoEntity.setEstado(permisoDto.isEstado());
        return permisoEntity;
    }


    public static PermisoDto mapFromCommandInsertToDto(PermisoCommandInsert permisoCommandInsert){
        PermisoDto permisoDto = new PermisoDto();
        permisoDto.setTipo(permisoCommandInsert.getTipo());
        permisoDto.declararDisponibilidad(EstadoD.ACTIVO);
        return permisoDto;
    }

    public static PermisoDto mapFromCommandUpdateToDto(PermisoCommandUpdate permisoCommandUpdate){
        PermisoDto permisoDto = new PermisoDto();
        permisoDto.setId(permisoCommandUpdate.getId());
        permisoDto.setTipo(permisoCommandUpdate.getTipo());
        permisoDto.setEstado(permisoCommandUpdate.isEstado());
        return permisoDto;
    }

}
