package com.jma.productoservice.service;

import com.jma.productoservice.api.DetalleVentaResponse;
import com.jma.productoservice.service.common.ICrudCommon;

public interface DetalleVentaService <T> extends ICrudCommon<T> {

    DetalleVentaResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

}
