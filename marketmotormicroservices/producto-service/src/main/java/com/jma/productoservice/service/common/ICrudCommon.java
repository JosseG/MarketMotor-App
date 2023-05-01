package com.jma.productoservice.service.common;

import java.util.List;

public interface ICrudCommon<T> {

    T guardar(T object);
    List<T> obtenerTodos();

    void eliminar(Object id);

    T obtenerPorId(Object id);


}
