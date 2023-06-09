package com.jma.productoservice.usuario.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCommandUpdate {

    private Long id;

    private String alias;

    private String contrasena;


}
