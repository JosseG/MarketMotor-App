package com.jma.productoservice.detalleVenta.infrastructure.out;

import com.jma.productoservice.detalleVenta.domain.entity.DetalleVentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVentaEntity,Long> {
}
