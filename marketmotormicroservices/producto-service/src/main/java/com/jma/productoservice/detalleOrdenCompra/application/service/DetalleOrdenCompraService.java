package com.jma.productoservice.detalleOrdenCompra.application.service;

import com.jma.productoservice.detalleOrdenCompra.domain.response.DetalleOrdenCompraResponse;
import com.jma.productoservice.service.common.ICrudCommon;

public interface DetalleOrdenCompraService<T> extends ICrudCommon<T> {

    DetalleOrdenCompraResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);
}
