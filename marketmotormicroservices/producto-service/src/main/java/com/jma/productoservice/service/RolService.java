package com.jma.productoservice.service;

import com.jma.productoservice.dto.RolDto;
import com.jma.productoservice.service.common.ICrudCommon;

import java.util.List;

public interface RolService<T> extends ICrudCommon<T> {

    List<T> guardarTodos(List<T> list);
}
