package com.jma.productoservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long idProveedor;

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

    @Column(name = "estado")
    private boolean estado;

    @OneToOne()
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

}