package com.jma.productoservice.detalleVenta.domain.dto;

import com.jma.productoservice.producto.domain.dto.ProductoDto;
import com.jma.productoservice.venta.domain.dto.VentaDto;
import com.jma.productoservice.utils.EstadoD;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class DetalleVentaDto {


    private Long id;

    private int unidades;

    private LocalDateTime actualizadoEn;

    private LocalDateTime creadoEn;

    private ProductoDto producto;

    private VentaDto venta;

    private boolean estado;
    public void declararDisponibilidad(EstadoD estadoD){
        setEstado(Objects.requireNonNull(estadoD) == EstadoD.ACTIVO);
    }
}
