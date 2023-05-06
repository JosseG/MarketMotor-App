package com.jma.productoservice.service;


import com.jma.productoservice.dto.PermisoDto;
import com.jma.productoservice.dto.RolDto;
import com.jma.productoservice.service.common.ICrudCommon;

import java.util.List;

public interface RolService<T> extends ICrudCommon<T> {


    PermisoDto definirPermiso(Long idRol, Long idPermiso);
    RolDto removerPermiso(Long idRol, Long idPermiso);

    List<T> buscarRolesPorPermisosId(Long permisoId);

}
