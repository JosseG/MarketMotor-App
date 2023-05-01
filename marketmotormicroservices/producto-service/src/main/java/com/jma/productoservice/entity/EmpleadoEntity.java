package com.jma.productoservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jma.productoservice.utils.EstadoD;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "estado")
    private boolean estado;

    @OneToOne()
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    public void declararDisponibilidad(EstadoD estadoD){
        switch (estadoD){
            case ACTIVO:
                setEstado(true);break;
            default:
                setEstado(false);break;
        }
    }


}
