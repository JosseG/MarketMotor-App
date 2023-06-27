package com.jma.productoservice.ordenCompra.infrastructure.out;

import com.jma.productoservice.ordenCompra.domain.entity.OrdenCompraEntity;
import com.jma.productoservice.producto.domain.entity.ProductoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrdenCompraRepository extends JpaRepository<OrdenCompraEntity,Long> {

    Page<OrdenCompraEntity> findOrdenCompraEntitiesByConfirmado(boolean confirmado, Pageable pageable);
}
