package com.jma.productoservice.dto;

import com.jma.productoservice.utils.EstadoD;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProveedorDto implements Serializable {

    private Long idProveedor;

    private String razonSocial;

    private String nombreComercial;

    private String numeroRuc;

    private String correo;

    private String direccion;

    private String departamento;

    private String telefonoProveedor;

    private boolean estado;

    private UsuarioDto usuarioDto;

    public void declararDisponibilidad(EstadoD estadoD){
        switch (estadoD){
            case ACTIVO:
                setEstado(true);break;
            default:
                setEstado(false);break;
        }
    }
}
