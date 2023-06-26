package com.jma.productoservice.producto.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jma.productoservice.detalleOrdenCompra.domain.entity.DetalleOrdenCompraEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tb_producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @Column(name = "descripcion_producto")
    @NotBlank
    private String descripcion;

    @Column(name = "tipo_producto")
    @NotBlank
    private String tipo;

    @Column(name = "serial_producto")
    @NotBlank
    private String serial;

    @Column(name = "marca_producto")
    @NotBlank
    private String marca;

    @Column(name = "precio_producto")
    @PositiveOrZero
    private double precio;

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

    @Column(name = "stock")
    @PositiveOrZero
    private int stock;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private Set<DetalleOrdenCompraEntity> detallesOrdenCompra;

}
