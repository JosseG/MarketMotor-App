package com.jma.productoservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_permiso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermisoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Long id;

    @Column(name = "tipo_permiso")
    private String tipo;

    @Column(name = "estaod")
    private boolean estado;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "permisos")
    @JsonIgnore
    private Set<RolEntity> roles = new HashSet<>();



}
