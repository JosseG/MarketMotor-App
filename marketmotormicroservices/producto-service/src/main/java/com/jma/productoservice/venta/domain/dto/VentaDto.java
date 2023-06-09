package com.jma.productoservice.venta.domain.dto;

import com.jma.productoservice.cliente.domain.dto.ClienteDto;
import com.jma.productoservice.empleado.domain.dto.EmpleadoDto;
import com.jma.productoservice.utils.EstadoD;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class VentaDto {

    private Long id;

    private double preciototal;

    private LocalDateTime actualizadoEn;

    private LocalDateTime creadoEn;

    private boolean estado;

    private ClienteDto cliente;

    private EmpleadoDto empleado;

    public void declararDisponibilidad(EstadoD estadoD){
        setEstado(Objects.requireNonNull(estadoD) == EstadoD.ACTIVO);
    }
}
