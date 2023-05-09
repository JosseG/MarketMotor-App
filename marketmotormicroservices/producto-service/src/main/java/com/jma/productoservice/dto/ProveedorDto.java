package com.jma.productoservice.dto;

import com.jma.productoservice.utils.EstadoD;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class ProveedorDto implements Serializable {

    private Long id;

    private String razonSocial;

    private String nombreComercial;

    private String numeroRuc;

    private String correo;

    private String direccion;

    private String departamento;

    private String telefonoProveedor;

    private boolean estado;

    private LocalDateTime actualizadoEn;

    private LocalDateTime creadoEn;

    private UsuarioDto usuarioDto;

    public void declararDisponibilidad(EstadoD estadoD){
        setEstado(Objects.requireNonNull(estadoD) == EstadoD.ACTIVO);
    }
}
