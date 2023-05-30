package com.jma.productoservice.service;

import com.jma.productoservice.api.ClienteResponse;
import com.jma.productoservice.service.common.ICrudCommon;

public interface ClienteService<T> extends ICrudCommon<T> {

    ClienteResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

}
