package com.jma.productoservice.service;

import com.jma.productoservice.api.VentaResponse;
import com.jma.productoservice.service.common.ICrudCommon;

public interface VentaService<T> extends ICrudCommon<T> {

    VentaResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

}
