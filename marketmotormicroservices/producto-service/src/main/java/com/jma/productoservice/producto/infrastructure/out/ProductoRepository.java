package com.jma.productoservice.producto.infrastructure.out;

import com.jma.productoservice.producto.domain.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductoRepository extends JpaRepository<ProductoEntity,Long> {

    Page<ProductoEntity> findProductoEntitiesByDescripcionContaining(String descripcion, Pageable pageable);

}
