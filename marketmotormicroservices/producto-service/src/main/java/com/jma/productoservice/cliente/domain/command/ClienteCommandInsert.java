package com.jma.productoservice.cliente.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteCommandInsert {

    private String dni;

    private String nombre;

    private String apellido;

}
