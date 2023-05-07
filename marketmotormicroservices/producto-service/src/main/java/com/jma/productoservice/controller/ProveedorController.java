package com.jma.productoservice.controller;

import com.jma.productoservice.api.proveedor.ProveedorCommandInsert;
import com.jma.productoservice.api.proveedor.ProveedorCommandUpdate;
import com.jma.productoservice.dto.ProveedorDto;
import com.jma.productoservice.mapping.ProveedorMapper;
import com.jma.productoservice.service.ProveedorService;
import com.jma.productoservice.utils.EstadoD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    private final ProveedorService<ProveedorDto> proveedorService;

    @Autowired
    public ProveedorController(ProveedorService<ProveedorDto> proveedorService){
        this.proveedorService = proveedorService;
    }


    @PostMapping
    public ResponseEntity<ProveedorDto> guardar(@RequestBody ProveedorCommandInsert proveedorCommandInsert){

        ProveedorDto proveedorDtoObt = proveedorService.guardar(ProveedorMapper.mapFromCommandInsertToDto(proveedorCommandInsert));

        return ResponseEntity.ok(proveedorDtoObt);
    }


    @GetMapping
    public ResponseEntity<ProveedorDto> obtenerPorId(@PathVariable Long id){

        ProveedorDto proveedorDtoObt = proveedorService.obtenerPorId(id);

        return ResponseEntity.ok(proveedorDtoObt);
    }

    @PutMapping
    public ResponseEntity<ProveedorDto> actualizar(@RequestBody ProveedorCommandUpdate proveedorCommandUpdate) {

        ProveedorDto proveedorDtoObt = proveedorService.actualizar(ProveedorMapper.mapFromCommandUpdateToDto(proveedorCommandUpdate));

        return ResponseEntity.ok(proveedorDtoObt);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> desactivar(@PathVariable Long id){
        try {
            ProveedorDto proveedor = proveedorService.obtenerPorId(id);
            if (proveedor == null)
                return ResponseEntity.notFound().build();

            proveedor.declararDisponibilidad(EstadoD.INACTIVO);
            proveedorService.guardar(proveedor);
            return ResponseEntity.ok("Se desactivó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar (@PathVariable Long id) {
        String respuesta = proveedorService.eliminar(id);

        return ResponseEntity.ok(respuesta);
    }

}