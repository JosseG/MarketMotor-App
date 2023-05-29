package com.jma.productoservice.repository;

import com.jma.productoservice.entity.OrdenCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrdenCompraRepository extends JpaRepository<OrdenCompraEntity,Long> {
}
