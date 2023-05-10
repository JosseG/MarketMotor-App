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
@Table(name = "tb_empleado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long id;

    @Column(name = "nombre_empleado")
    private String nombre;

    @Column(name = "apellidoPat_empleado")
    private String apellidoPat;

    @Column(name = "apellidoMat_empleado")
    private String apellidoMat;

    @Column(name = "telefono_empleado")
    private String telefono;

    @Column(name = "correo_empleado")
    private String correo;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;


    @OneToMany(mappedBy = "empleado")
    @JsonIgnore
    private Set<OrdenCompraEntity> ordenesCompra;


}
