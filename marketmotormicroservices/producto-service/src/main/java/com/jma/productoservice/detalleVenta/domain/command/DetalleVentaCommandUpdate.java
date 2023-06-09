package com.jma.productoservice.detalleVenta.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleVentaCommandUpdate {

    private Long id;

    private int unidades;

}
