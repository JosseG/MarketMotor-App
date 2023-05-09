package com.jma.productoservice.service;

import com.jma.productoservice.api.DetalleOrdenCompraResponse;
import com.jma.productoservice.service.common.ICrudCommon;

public interface DetalleOrdenCompraService<T> extends ICrudCommon<T> {

    DetalleOrdenCompraResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);
}
