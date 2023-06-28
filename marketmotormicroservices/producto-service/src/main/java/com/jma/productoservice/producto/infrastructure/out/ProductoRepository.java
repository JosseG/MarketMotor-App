package com.jma.productoservice.producto.infrastructure.out;

import com.jma.productoservice.empleado.domain.entity.EmpleadoEntity;
import com.jma.productoservice.producto.domain.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProductoRepository extends JpaRepository<ProductoEntity,Long> {

    Page<ProductoEntity> findProductoEntitiesByDescripcionContaining(String descripcion, Pageable pageable);


    /*@Query(value = "select p from ProductoEntity p where p.estado = true")
    List<ProductoEntity> findProductEntitiesAvailable();*/

}
