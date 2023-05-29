package com.jma.productoservice.repository;

import com.jma.productoservice.entity.DetalleOrdenCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface DetalleOrdenCompraRepository extends JpaRepository<DetalleOrdenCompraEntity,Long> {
}
