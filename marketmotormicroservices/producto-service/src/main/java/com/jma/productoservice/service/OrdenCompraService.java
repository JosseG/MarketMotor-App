package com.jma.productoservice.service;

import com.jma.productoservice.api.OrdenCompraResponse;
import com.jma.productoservice.service.common.ICrudCommon;

public interface OrdenCompraService<T> extends ICrudCommon<T> {

    OrdenCompraResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

}
