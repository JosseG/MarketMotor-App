package com.jma.productoservice.cliente.domain.dto;


import com.jma.productoservice.utils.EstadoD;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class ClienteDto {

    private Long id;

    private String dni;

    private String nombre;

    private String apellido;

    private LocalDateTime actualizadoEn;

    private LocalDateTime creadoEn;

    private boolean estado;

    public void declararDisponibilidad(EstadoD estadoD){
        setEstado(Objects.requireNonNull(estadoD) == EstadoD.ACTIVO);
    }

}
