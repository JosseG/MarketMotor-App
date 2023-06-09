package com.jma.productoservice.empleado.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoCommandUpdate {

    private Long id;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String telefono;

    private String correo;

    private boolean estado;
}
