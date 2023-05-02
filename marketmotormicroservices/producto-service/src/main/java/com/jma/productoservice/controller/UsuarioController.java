package com.jma.productoservice.controller;

import com.jma.productoservice.dto.UsuarioDto;
import com.jma.productoservice.entity.UsuarioEntity;
import com.jma.productoservice.mapping.UsuarioMapper;
import com.jma.productoservice.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService<UsuarioEntity> usuarioService;

    @Autowired
    public UsuarioController(UsuarioService<UsuarioEntity> usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> obtenerTodos(){
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @PostMapping()
    public ResponseEntity<UsuarioEntity> guardar(@RequestBody UsuarioDto usuarioDto){

        UsuarioEntity usuarioGuardado = usuarioService.guardar(UsuarioMapper.mapToEntity(usuarioDto));

        return ResponseEntity.ok(usuarioGuardado);

    }



}
