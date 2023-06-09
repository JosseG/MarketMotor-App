package com.jma.productoservice.producto.domain.dto;

import com.jma.productoservice.utils.EstadoD;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class ProductoDto implements Serializable{

    private Long id;

    private String descripcion;

    private String tipo;

    private String serial;

    private String marca;

    private double precio;

    private boolean estado;

    private LocalDateTime actualizadoEn;

    private LocalDateTime creadoEn;

    public void declararDisponibilidad(EstadoD estadoD){
        setEstado(Objects.requireNonNull(estadoD) == EstadoD.ACTIVO);
    }
}
