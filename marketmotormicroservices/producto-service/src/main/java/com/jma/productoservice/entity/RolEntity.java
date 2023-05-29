package com.jma.productoservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "tb_permiso_rol",
            joinColumns = {@JoinColumn(name = "id_rol")},
            inverseJoinColumns = {@JoinColumn(name = "id_permiso")}

    )
    private Set<PermisoEntity> permisos = new HashSet<>();


    @OneToMany(mappedBy = "rol")
    @JsonIgnore
    private Set<UsuarioEntity> usuarios;



    public void agregarPermiso(PermisoEntity permisoEntity) {
        this.permisos.add(permisoEntity);
        permisoEntity.getRoles().add(this);
    }

    public void removerPermiso(long permisoId) {
        PermisoEntity permiso = this.permisos.stream().filter(t -> getId() == permisoId).findFirst().orElse(null);
        if (permiso != null) {
            this.permisos.remove(permiso);
            permiso.getRoles().remove(this);
        }
    }


}
