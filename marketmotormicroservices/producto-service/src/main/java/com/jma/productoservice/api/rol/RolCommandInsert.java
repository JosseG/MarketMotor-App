package com.jma.productoservice.api.rol;

import com.jma.productoservice.dto.PermisoDto;
import com.jma.productoservice.entity.PermisoEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RolCommandInsert {
    private String nombre;
}
