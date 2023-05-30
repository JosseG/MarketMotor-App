package com.jma.productoservice.repository;

import com.jma.productoservice.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProveedorRepository extends JpaRepository<ProveedorEntity,Long> {
}