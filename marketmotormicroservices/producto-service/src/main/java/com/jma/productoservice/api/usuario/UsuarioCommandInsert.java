package com.jma.productoservice.api.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCommandInsert {

    private String alias;

    private String contrasena;

    private String estado;
}
