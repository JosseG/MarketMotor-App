package com.jma.productoservice.dto;

import com.jma.productoservice.utils.EstadoD;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EmpleadoDto implements Serializable {

    private Long id;

    private String nombre;

    private String apellidoPat;

    private String apellidoMat;

    private String telefono;

    private String correo;

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