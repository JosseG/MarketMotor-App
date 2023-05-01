package com.jma.productoservice.service;

import com.jma.productoservice.api.EmpleadoResponse;
import com.jma.productoservice.service.common.ICrudCommon;

public interface EmpleadoService<T> extends ICrudCommon<T> {

    EmpleadoResponse obtenerTodosP(int pageNo, int pageSize, String sortBy, String sortDir);

}
