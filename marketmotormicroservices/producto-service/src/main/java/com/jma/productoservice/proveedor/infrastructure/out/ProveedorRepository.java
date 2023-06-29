package com.jma.productoservice.proveedor.infrastructure.out;

import com.jma.productoservice.empleado.domain.entity.EmpleadoEntity;
import com.jma.productoservice.proveedor.domain.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ProveedorRepository extends JpaRepository<ProveedorEntity,Long> {

    @Query(value = "select p from ProveedorEntity p inner join UsuarioEntity u on p.usuario.id = u.id where u.alias = :alias")
    Optional<ProveedorEntity> findProveedorEntityByUserAlias(String alias);
}