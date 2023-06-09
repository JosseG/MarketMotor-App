package com.jma.productoservice.rol.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jma.productoservice.usuario.domain.entity.UsuarioEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tb_rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id;

    @Column(name = "nombre_rol")
    @NotBlank
    private String nombre;

    @Column(name = "estado")
    private boolean estado;


    @OneToMany(mappedBy = "rol")
    @JsonIgnore
    private Set<UsuarioEntity> usuarios;


}
