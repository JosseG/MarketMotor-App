package com.jma.productoservice.mapping;


import com.jma.productoservice.api.usuario.UsuarioCommandInsert;
import com.jma.productoservice.api.usuario.UsuarioCommandUpdate;
import com.jma.productoservice.dto.UsuarioDto;
import com.jma.productoservice.entity.UsuarioEntity;
import com.jma.productoservice.utils.EstadoD;

public class UsuarioMapper {

    public static UsuarioEntity mapToEntity(UsuarioDto usuarioDto){

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setAlias(usuarioDto.getAlias());
        usuarioEntity.setEstado(usuarioDto.isEstado());
        return usuarioEntity;
    }

    public static UsuarioDto mapToDto(UsuarioEntity usuarioEntity){
        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setId(usuarioEntity.getId());
        usuarioDto.setAlias(usuarioEntity.getAlias());
        usuarioDto.setCreadoEn(usuarioEntity.getCreadoEn());
        usuarioDto.setActualizadoEn(usuarioEntity.getActualizadoEn());
        usuarioDto.setEstado(usuarioEntity.isEstado());
        return usuarioDto;
    }

    public static UsuarioDto mapFromCommandInsertToDto(UsuarioCommandInsert usuarioCommandInsert){
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setAlias(usuarioCommandInsert.getAlias());
        usuarioDto.declararDisponibilidad(EstadoD.ACTIVO);
        return usuarioDto;
    }

    public static UsuarioDto mapFromCommandUpdateToDto(UsuarioCommandUpdate usuarioCommandUpdate){
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuarioCommandUpdate.getId());
        usuarioDto.setAlias(usuarioCommandUpdate.getAlias());
        return usuarioDto;
    }


}
