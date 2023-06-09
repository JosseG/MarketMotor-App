package com.jma.productoservice.cliente.application.service;

import com.jma.productoservice.cliente.domain.response.ClienteResponse;
import com.jma.productoservice.service.common.ICrudCommon;

public interface ClienteService<T> extends ICrudCommon<T> {

    ClienteResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

}
