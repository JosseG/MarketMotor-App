package com.jma.productoservice.api.venta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleVentaCommandInsert {

    private int unidades;

    private Long idProducto;

    private Long idVenta;

}
