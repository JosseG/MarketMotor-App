package com.jma.productoservice.producto.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoCommandInsert {

    private String tipo;

    private String marca;

    private String serial;

    private String descripcion;

    private double precio;

}
