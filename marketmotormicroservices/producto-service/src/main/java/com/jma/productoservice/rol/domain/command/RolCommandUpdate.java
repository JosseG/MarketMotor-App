package com.jma.productoservice.rol.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolCommandUpdate {
    private Long id;
    private String nombre;
    private boolean estado;
}
