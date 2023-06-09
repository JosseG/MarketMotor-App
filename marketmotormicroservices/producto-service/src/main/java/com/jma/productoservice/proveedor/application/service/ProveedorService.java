package com.jma.productoservice.proveedor.application.service;

import com.jma.productoservice.proveedor.domain.response.ProveedorResponse;
import com.jma.productoservice.utils.ICrudCommon;

import java.util.List;

public interface ProveedorService<T> extends ICrudCommon<T> {

    ProveedorResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);
    List<T> guardarTodos(List<T> list);
}

