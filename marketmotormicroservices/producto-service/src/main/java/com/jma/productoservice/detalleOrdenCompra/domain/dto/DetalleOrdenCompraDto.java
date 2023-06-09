package com.jma.productoservice.detalleOrdenCompra.domain.dto;

import com.jma.productoservice.ordenCompra.domain.dto.OrdenCompraDto;
import com.jma.productoservice.producto.domain.dto.ProductoDto;
import com.jma.productoservice.utils.EstadoD;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class DetalleOrdenCompraDto implements Serializable {

    private Long id;

    private int cantidad;

    private double precioUnitario;

    private LocalDateTime actualizadoEn;

    private LocalDateTime creadoEn;

    private boolean estado;

    private ProductoDto producto;

    private OrdenCompraDto ordenCompra;
    public void declararDisponibilidad(EstadoD estadoD){
        setEstado(Objects.requireNonNull(estadoD) == EstadoD.ACTIVO);
    }
}
