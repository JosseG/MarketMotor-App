package com.jma.productoservice.api.empleado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoCommandInsert {

    private String nombre;

    private String apellidoPat;

    private String apellidoMat;

    private String telefono;

    private String correo;

    private Long idUsuario;

}
