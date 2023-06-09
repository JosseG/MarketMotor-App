package com.jma.productoservice.usuario.application.mapper;


import com.jma.productoservice.rol.domain.dto.RolDto;
import com.jma.productoservice.usuario.domain.command.UsuarioCommandInsert;
import com.jma.productoservice.usuario.domain.command.UsuarioCommandUpdate;
import com.jma.productoservice.usuario.domain.dto.UsuarioDto;
import com.jma.productoservice.usuario.domain.entity.UsuarioEntity;
import com.jma.productoservice.utils.EstadoD;

public class UsuarioMapper {

    public static UsuarioEntity mapToEntity(UsuarioDto usuarioDto){

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setAlias(usuarioDto.getAlias());
        usuarioEntity.setContrasena(usuarioDto.getContrasena());
        usuarioEntity.setEstado(usuarioDto.isEstado());
        return usuarioEntity;
    }

    public static UsuarioDto mapToDto(UsuarioEntity usuarioEntity){
        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setId(usuarioEntity.getId());
        usuarioDto.setAlias(usuarioEntity.getAlias());
        usuarioDto.setContrasena(usuarioEntity.getContrasena());
        usuarioDto.setCreadoEn(usuarioEntity.getCreadoEn());
        usuarioDto.setActualizadoEn(usuarioEntity.getActualizadoEn());
        usuarioDto.setEstado(usuarioEntity.isEstado());
        return usuarioDto;
    }

    public static UsuarioDto mapFromCommandInsertToDto(UsuarioCommandInsert usuarioCommandInsert){

        RolDto rolDto = new RolDto();
        rolDto.setId(usuarioCommandInsert.getIdRol());

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setAlias(usuarioCommandInsert.getAlias());
        usuarioDto.setContrasena(usuarioCommandInsert.getContrasena());
        usuarioDto.declararDisponibilidad(EstadoD.ACTIVO);
        usuarioDto.setRol(rolDto);
        return usuarioDto;
    }

    public static UsuarioDto mapFromCommandUpdateToDto(UsuarioCommandUpdate usuarioCommandUpdate){
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuarioCommandUpdate.getId());
        usuarioDto.setContrasena(usuarioCommandUpdate.getContrasena());
        usuarioDto.setAlias(usuarioCommandUpdate.getAlias());
        return usuarioDto;
    }


}
