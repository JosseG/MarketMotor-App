package com.jma.productoservice.empleado.application.service;

import com.jma.productoservice.empleado.domain.response.EmpleadoResponse;
import com.jma.productoservice.service.common.ICrudCommon;

import java.util.List;

public interface EmpleadoService<T> extends ICrudCommon<T> {

    EmpleadoResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

    List<T> guardarTodos(List<T> list);

}
