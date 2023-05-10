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
    private String descripcion;

    @Column(name = "tipo_producto")
    private String tipo;

    @Column(name = "serial_producto")
    private String serial;

    @Column(name = "marca_producto")
    private String marca;

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

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private Set<DetalleOrdenCompraEntity> detallesOrdenCompra;

}
