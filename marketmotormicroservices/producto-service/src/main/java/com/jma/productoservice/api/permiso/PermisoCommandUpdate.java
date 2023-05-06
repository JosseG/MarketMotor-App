package com.jma.productoservice.api.permiso;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermisoCommandUpdate {

    private Long id;

    private String tipo;

    private boolean estado;

}
