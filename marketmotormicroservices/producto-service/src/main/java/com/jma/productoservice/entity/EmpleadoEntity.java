package com.jma.productoservice.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jma.productoservice.utils.EstadoD;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

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
    @Column(name = "creado_en")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creadoEn;

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
