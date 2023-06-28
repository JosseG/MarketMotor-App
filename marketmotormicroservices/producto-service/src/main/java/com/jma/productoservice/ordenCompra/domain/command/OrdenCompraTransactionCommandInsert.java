package com.jma.productoservice.ordenCompra.domain.command;

import com.jma.productoservice.detalleOrdenCompra.domain.command.DetalleOrdenCompraCommandInsert;
import com.jma.productoservice.detalleVenta.domain.command.DetalleVentaCommandInsert;
import com.jma.productoservice.venta.domain.command.VentaCommandInsert;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrdenCompraTransactionCommandInsert {

    private OrdenCompraCommandInsert ordenCompra;
    private List<DetalleOrdenCompraCommandInsert> detallesOrdenCompra;
}
