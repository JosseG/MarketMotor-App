package com.jma.productoservice.venta.domain.command;

import com.jma.productoservice.detalleVenta.domain.command.DetalleVentaCommandInsert;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VentaTransactionCommandInsert {

    private VentaCommandInsert venta;
    private List<DetalleVentaCommandInsert> detallesVenta;
}
