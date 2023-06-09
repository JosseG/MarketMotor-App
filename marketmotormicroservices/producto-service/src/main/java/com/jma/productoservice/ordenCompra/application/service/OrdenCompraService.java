package com.jma.productoservice.ordenCompra.application.service;

import com.jma.productoservice.ordenCompra.domain.response.OrdenCompraResponse;
import com.jma.productoservice.utils.ICrudCommon;

public interface OrdenCompraService<T> extends ICrudCommon<T> {

    OrdenCompraResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

}
