package com.jma.productoservice.detalleOrdenCompra.infrastructure.out;

import com.jma.productoservice.detalleOrdenCompra.domain.entity.DetalleOrdenCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DetalleOrdenCompraRepository extends JpaRepository<DetalleOrdenCompraEntity,Long> {
}
