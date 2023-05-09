package com.jma.productoservice.api.ordencompra;

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
