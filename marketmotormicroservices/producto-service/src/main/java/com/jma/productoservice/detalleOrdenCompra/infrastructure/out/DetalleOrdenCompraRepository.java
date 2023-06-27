package com.jma.productoservice.detalleOrdenCompra.infrastructure.out;

import com.jma.productoservice.detalleOrdenCompra.domain.entity.DetalleOrdenCompraEntity;
import com.jma.productoservice.producto.domain.entity.ProductoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DetalleOrdenCompraRepository extends JpaRepository<DetalleOrdenCompraEntity,Long> {

    Page<DetalleOrdenCompraEntity> findDetalleOrdenCompraEntitiesByOrdenCompra_Id(Long id, Pageable pageable);

    Page<DetalleOrdenCompraEntity> findDetalleOrdenCompraEntitiesByOrdenCompra_Empleado_Id(Long id, Pageable pageable);

    Page<DetalleOrdenCompraEntity> findDetalleOrdenCompraEntitiesByProducto_Id(Long id, Pageable pageable);

}
