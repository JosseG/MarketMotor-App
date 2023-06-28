package com.jma.productoservice.venta.application.service;

import com.jma.productoservice.detalleVenta.domain.dto.DetalleVentaDto;
import com.jma.productoservice.utils.ICrudCommon;
import com.jma.productoservice.venta.domain.response.VentaResponse;

import java.util.List;

public interface VentaService<T> extends ICrudCommon<T> {

    VentaResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

    boolean realizarVenta(T a, List<DetalleVentaDto> detalles) throws Exception;

}
