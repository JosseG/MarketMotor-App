package com.jma.productoservice.service;

import com.jma.productoservice.api.ProductoResponse;
import com.jma.productoservice.service.common.ICrudCommon;

import java.util.List;

public interface ProductoService<T> extends ICrudCommon<T> {

    List<T> guardarTodos(List<T> list);

    ProductoResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);
}