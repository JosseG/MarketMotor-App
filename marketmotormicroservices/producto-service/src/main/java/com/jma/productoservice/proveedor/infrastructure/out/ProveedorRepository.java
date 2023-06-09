package com.jma.productoservice.proveedor.infrastructure.out;

import com.jma.productoservice.proveedor.domain.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProveedorRepository extends JpaRepository<ProveedorEntity,Long> {
}