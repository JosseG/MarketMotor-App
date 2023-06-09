package com.jma.productoservice.ordenCompra.domain.command;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrdenCompraCommandInsert {

    private int numero;

    private LocalDateTime fecha;

    private double valorTotal;

    private Long idEmpleado;

    private Long idProveedor;

}
