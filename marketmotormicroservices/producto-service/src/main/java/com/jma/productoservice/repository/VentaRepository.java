package com.jma.productoservice.repository;

import com.jma.productoservice.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<VentaEntity,Long> {
}
