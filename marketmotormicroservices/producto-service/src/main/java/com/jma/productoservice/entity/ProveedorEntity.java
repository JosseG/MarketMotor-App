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
@Table(name = "tb_proveedor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long id;

    @Column(name = "razon_social_proveedor")
    private String razonSocial;

    @Column(name = "nombre_comercial_proveedor")
    private String nombreComercial;

    @Column(name = "numero_ruc_proveedor")
    private String numeroRuc;

    @Column(name = "correo_proveedor")
    private String correo;

    @Column(name = "direccion_proveedor")
    private String direccion;

    @Column(name = "departamento_proveedor")
    private String departamento;

    @Column(name = "telefono_proveedor")
    private String telefonoProveedor;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "actualizado_en")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime actualizadoEn;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "creado_en")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creadoEn;

    @Column(name = "estado")
    private boolean estado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "proveedor")
    @JsonIgnore
    private Set<OrdenCompraEntity> ordenesCompra;


}