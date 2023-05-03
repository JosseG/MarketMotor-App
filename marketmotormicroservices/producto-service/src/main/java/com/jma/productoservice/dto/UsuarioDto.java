package com.jma.productoservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class UsuarioDto implements Serializable {

    private Long id;

    private String alias;

    private LocalDateTime actualizadoEn;

    private LocalDateTime creadoEn;

}
