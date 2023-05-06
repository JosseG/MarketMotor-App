package com.jma.productoservice.api.empleado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoCommandUpdate {

    private Long id;

    private String nombre;

    private String apellidoPat;

    private String apellidoMat;

    private String telefono;

    private String correo;

    private boolean estado;
}
