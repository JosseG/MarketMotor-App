package com.jma.productoservice.empleado.infrastructure.out;

import com.jma.productoservice.empleado.domain.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity,Long> {

    @Query(value = "select e from EmpleadoEntity e inner join UsuarioEntity u on e.usuario.id = u.id where u.alias = :alias")
    Optional<EmpleadoEntity> findEmpleadoEntityByUserAlias(String alias);

}
