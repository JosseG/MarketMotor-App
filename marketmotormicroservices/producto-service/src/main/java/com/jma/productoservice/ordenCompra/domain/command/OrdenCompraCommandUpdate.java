package com.jma.productoservice.ordenCompra.domain.command;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrdenCompraCommandUpdate {

    private Long id;

    private int numero;

    private boolean confirmacion;

    private LocalDateTime fecha;

    private double valorTotal;

}
