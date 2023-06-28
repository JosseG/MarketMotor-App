package com.jma.productoservice.detalleVenta.infrastructure.out;

import com.jma.productoservice.detalleVenta.domain.entity.DetalleVentaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVentaEntity,Long> {


    Page<DetalleVentaEntity> findDetalleVentaEntitiesByVenta_Empleado_Id(Long id, Pageable pageable);

    Page<DetalleVentaEntity> findDetalleVentaEntitiesByProducto_Id(Long id, Pageable pageable);

    List<DetalleVentaEntity> findDetalleVentaEntitiesByProducto_Id(Long id);


    @Query("SELECT d FROM DetalleVentaEntity  d WHERE d.creadoEn BETWEEN :inicio AND :fin")
    List<DetalleVentaEntity> buscarPorFechaCreacion(Date inicio, Date fin);

    @Query("SELECT d FROM DetalleVentaEntity  d WHERE d.creadoEn BETWEEN :inicio AND :fin")
    Page<DetalleVentaEntity> buscarPorFechaCreacion(LocalDateTime inicio, LocalDateTime fin, Pageable pageable);
    
}
