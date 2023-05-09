package com.jma.productoservice.service;

import com.jma.productoservice.api.ProveedorResponse;
import com.jma.productoservice.service.common.ICrudCommon;

import java.util.List;

public interface ProveedorService<T> extends ICrudCommon<T> {

    ProveedorResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);
    List<T> guardarTodos(List<T> list);
}

