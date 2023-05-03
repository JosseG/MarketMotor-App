package com.jma.productoservice.controller;

import com.jma.productoservice.api.usuario.UsuarioCommandInsert;
import com.jma.productoservice.dto.UsuarioDto;
import com.jma.productoservice.mapping.UsuarioMapper;
import com.jma.productoservice.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService<UsuarioDto> usuarioService;

    @Autowired
    public UsuarioController(UsuarioService<UsuarioDto> usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> obtenerTodos(){
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @PostMapping()
    public ResponseEntity<UsuarioDto> guardar(@RequestBody UsuarioCommandInsert usuarioCommandInsert){

        UsuarioDto usuarioGuardado = usuarioService.guardar(UsuarioMapper.mapFromCommandInsertToDto(usuarioCommandInsert));
        return ResponseEntity.ok(usuarioGuardado);

    }

    @PostMapping("/guardarTodos")
    public ResponseEntity<List<UsuarioDto>> guardarTodos(@RequestBody List<UsuarioCommandInsert> UsuariosCommand){

        List<UsuarioDto> usuariosMappeados = UsuariosCommand.stream().map(UsuarioMapper::mapFromCommandInsertToDto).toList();
        List<UsuarioDto> usuariosGuardados = usuarioService.guardarTodos(usuariosMappeados);

        return ResponseEntity.ok(usuariosGuardados);

    }



}
