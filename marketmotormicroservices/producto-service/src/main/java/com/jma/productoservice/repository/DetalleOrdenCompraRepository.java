package com.jma.productoservice.repository;

import com.jma.productoservice.entity.DetalleOrdenCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DetalleOrdenCompraRepository extends JpaRepository<DetalleOrdenCompraEntity,Long> {
}
