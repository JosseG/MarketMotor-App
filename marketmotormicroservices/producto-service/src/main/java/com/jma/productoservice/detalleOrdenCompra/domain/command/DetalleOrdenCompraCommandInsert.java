package com.jma.productoservice.detalleOrdenCompra.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleOrdenCompraCommandInsert {

    private int cantidad;

    private double precioUnitario;

    private Long idProducto;

    private Long idOrdenCompra;

}
