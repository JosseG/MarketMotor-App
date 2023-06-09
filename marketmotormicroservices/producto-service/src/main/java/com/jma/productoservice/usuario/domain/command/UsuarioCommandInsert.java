package com.jma.productoservice.usuario.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCommandInsert {

    private String alias;

    private String contrasena;

    private Long idRol;

}
