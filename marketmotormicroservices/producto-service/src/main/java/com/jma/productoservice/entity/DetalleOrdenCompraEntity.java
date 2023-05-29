package com.jma.productoservice.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "tb_det_orden_compra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleOrdenCompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_det_orden_compra")
    private Long id;

    @Column(name = "cantidad_det_orden_compra")
    @PositiveOrZero
    private int cantidad;

    @Column(name = "preciou_det_orden_compra")
    @PositiveOrZero
    private double precioUnitario;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "actualizado_en")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime actualizadoEn;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "creado_en",updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creadoEn;

    @Column(name = "estado")
    private boolean estado;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    @NotNull
    private ProductoEntity producto;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_compra")
    @NotNull
    private OrdenCompraEntity ordenCompra;

}
