package com.jma.productoservice.repository;

import com.jma.productoservice.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface ProductoRepository extends JpaRepository<ProductoEntity,Long> {

    Page<ProductoEntity> findProductoEntitiesByDescripcionContaining(String descripcion, Pageable pageable);

}
