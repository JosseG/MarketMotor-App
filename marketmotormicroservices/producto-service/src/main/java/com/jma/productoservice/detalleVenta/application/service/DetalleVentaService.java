package com.jma.productoservice.detalleVenta.application.service;

import com.jma.productoservice.detalleVenta.domain.response.DetalleVentaResponse;
import com.jma.productoservice.utils.ICrudCommon;

public interface DetalleVentaService <T> extends ICrudCommon<T> {

    DetalleVentaResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

}
