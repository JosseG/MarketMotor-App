package com.jma.productoservice.ordenCompra.infrastructure.out;

import com.jma.productoservice.ordenCompra.domain.entity.OrdenCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdenCompraRepository extends JpaRepository<OrdenCompraEntity,Long> {
}
