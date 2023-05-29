package com.jma.productoservice.controller;

import com.jma.productoservice.api.permiso.PermisoCommandInsert;
import com.jma.productoservice.api.permiso.PermisoCommandUpdate;
import com.jma.productoservice.dto.PermisoDto;
import com.jma.productoservice.mapping.PermisoMapper;
import com.jma.productoservice.service.PermisoService;
import com.jma.productoservice.utils.EstadoD;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permisos")
@Validated
public class PermisoController {

    private final PermisoService<PermisoDto> permisoService;

    @Autowired
    public PermisoController(PermisoService<PermisoDto> permisoService){
        this.permisoService = permisoService;
    }


    @PostMapping
    public ResponseEntity<PermisoDto> guardar(@RequestBody @Valid PermisoCommandInsert permisoCommandInsert){

        PermisoDto permisoDto = permisoService.guardar(PermisoMapper.mapFromCommandInsertToDto(permisoCommandInsert));

        return ResponseEntity.ok(permisoDto);
    }

    @PostMapping("/guardarTodos")
    public ResponseEntity<List<PermisoDto>> guardarTodos(@RequestBody @Valid List<PermisoCommandInsert> permisoCommandInserts){

        List<PermisoDto> permisosMappeados = permisoCommandInserts.stream().map(PermisoMapper::mapFromCommandInsertToDto).toList();
        List<PermisoDto> permisosGuardados = permisoService.guardarTodos(permisosMappeados);

        return ResponseEntity.ok(permisosGuardados);

    }

    @GetMapping
    public ResponseEntity<List<PermisoDto>> obtenerTodos(){

        List<PermisoDto> permisoDtos = permisoService.obtenerTodos();

        return ResponseEntity.ok(permisoDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermisoDto> obtenerPorId(@PathVariable Long id){

        PermisoDto permisoDto = permisoService.obtenerPorId(id);

        return ResponseEntity.ok(permisoDto);
    }


    @PutMapping
    public ResponseEntity<PermisoDto> actualizar(@RequestBody @Valid PermisoCommandUpdate permisoCommandUpdate){

        PermisoDto permisoDto = permisoService.actualizar(PermisoMapper.mapFromCommandUpdateToDto(permisoCommandUpdate));

        return ResponseEntity.ok(permisoDto);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<String> desactivar(@PathVariable Long id){

        try{
            PermisoDto permiso = permisoService.obtenerPorId(id);
            if(permiso == null)
                return ResponseEntity.notFound().build();

            permiso.declararDisponibilidad(EstadoD.INACTIVO);
            permisoService.guardar(permiso);
            return ResponseEntity.ok("Se desactivó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){

        String respuesta = permisoService.eliminar(id);

        return ResponseEntity.ok(respuesta);
    }


}
