package com.jma.productoservice.rol.application.service;

import com.jma.productoservice.service.common.ICrudCommon;

import java.util.List;

public interface RolService<T> extends ICrudCommon<T> {

    List<T> guardarTodos(List<T> list);
}
