package com.jma.productoservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tb_orden_compra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenCompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden_compra")
    private Long id;

    @Column(name = "confirmado_orden_compra")
    private boolean confirmado;

    @Column(name = "numero_orden_compra")
    private int numero;

    @Column(name = "fecha_orden_compra")
    private LocalDateTime fecha;

    @Column(name = "valort_orden_compra")
    private double valorTotal;

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
    @JoinColumn(name = "id_empleado",updatable = false,nullable = false)
    private EmpleadoEntity empleado;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor",updatable = false,nullable = false)
    private ProveedorEntity proveedor;

    @OneToMany(mappedBy = "ordenCompra")
    @JsonIgnore
    private Set<DetalleOrdenCompraEntity> detallesOrdenCompra;



}
