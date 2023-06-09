package com.jma.productoservice.proveedor.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProveedorCommandInsert {

    private String razonSocial;

    private String nombreComercial;

    private String numeroRuc;

    private String correo;

    private String direccion;

    private String departamento;

    private String telefonoProveedor;

    private Long idUsuario;
}
