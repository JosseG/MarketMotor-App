package com.jma.productoservice.api.venta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaCommandInsert {

    private double preciototal;

    private Long idCliente;

    private Long idEmpleado;

}
