package com.jma.productoservice.service;



import com.jma.productoservice.service.common.ICrudCommon;

import java.util.List;

public interface PermisoService<T> extends ICrudCommon<T> {
    List<T> buscarPermisosPorRolId(Long rolId);
}
