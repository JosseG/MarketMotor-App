package com.jma.productoservice.dto;

import com.jma.productoservice.utils.EstadoD;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class UsuarioDto implements Serializable {

    private Long id;

    private String alias;

    private String contrasena;

    private LocalDateTime actualizadoEn;

    private LocalDateTime creadoEn;

    private boolean estado;

    private RolDto rol;

    public void declararDisponibilidad(EstadoD estadoD){
        setEstado(Objects.requireNonNull(estadoD) == EstadoD.ACTIVO);
    }
}
