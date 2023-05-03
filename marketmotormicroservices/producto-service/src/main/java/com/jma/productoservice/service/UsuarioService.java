package com.jma.productoservice.service;



import com.jma.productoservice.service.common.ICrudCommon;

import java.util.List;

public interface UsuarioService<T> extends ICrudCommon<T> {

    List<T> guardarTodos(List<T> list);
}
