package com.jma.productoservice.api.producto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoCommandUpdate {

    private Long id;

    private String tipo;

    private String marca;

    private String serial;

    private String descripcion;

}
