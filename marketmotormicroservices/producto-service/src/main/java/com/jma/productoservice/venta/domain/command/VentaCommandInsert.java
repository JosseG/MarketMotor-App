package com.jma.productoservice.venta.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaCommandInsert {

    private double preciototal;

    private Long idCliente;

    private Long idEmpleado;

}
