package com.jma.productoservice.empleado.domain.dto;

import com.jma.productoservice.usuario.domain.dto.UsuarioDto;
import com.jma.productoservice.utils.EstadoD;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class EmpleadoDto implements Serializable {

    private Long id;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String telefono;

    private String correo;

    private boolean estado;

    private LocalDateTime actualizadoEn;

    private LocalDateTime creadoEn;

    private UsuarioDto usuarioDto;

    public void declararDisponibilidad(EstadoD estadoD){
        setEstado(Objects.requireNonNull(estadoD) == EstadoD.ACTIVO);
    }
}
