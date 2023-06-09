package com.jma.productoservice.venta.infrastructure.out;

import com.jma.productoservice.venta.domain.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<VentaEntity,Long> {
}
