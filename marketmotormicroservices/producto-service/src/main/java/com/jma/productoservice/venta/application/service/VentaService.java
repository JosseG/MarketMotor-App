package com.jma.productoservice.venta.application.service;

import com.jma.productoservice.utils.ICrudCommon;
import com.jma.productoservice.venta.domain.response.VentaResponse;

public interface VentaService<T> extends ICrudCommon<T> {

    VentaResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

}
