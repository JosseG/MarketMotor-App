package com.jma.productoservice.mapping;

import com.jma.productoservice.dto.UsuarioDto;
import com.jma.productoservice.entity.UsuarioEntity;

public class UsuarioMapper {

    public static UsuarioEntity mapToEntity(UsuarioDto usuarioDto){

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setAlias(usuarioDto.getAlias());

        return usuarioEntity;
    }

    public static UsuarioDto mapToDto(UsuarioEntity usuarioEntity){
        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setAlias(usuarioEntity.getAlias());
        return usuarioDto;
    }


}
