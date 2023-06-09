package com.jma.productoservice.empleado.infrastructure.out;

import com.jma.productoservice.empleado.domain.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity,Long> {


}
