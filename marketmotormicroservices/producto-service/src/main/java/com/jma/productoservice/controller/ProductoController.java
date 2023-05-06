package com.jma.productoservice.controller;


import com.jma.productoservice.api.producto.ProductoCommandInsert;
import com.jma.productoservice.api.producto.ProductoCommandUpdate;
import com.jma.productoservice.dto.ProductoDto;
import com.jma.productoservice.mapping.ProductoMapper;
import com.jma.productoservice.service.ProductoService;
import com.jma.productoservice.utils.EstadoD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService<ProductoDto> productoService;

    @Autowired
    public ProductoController(ProductoService<ProductoDto> productoService){
        this.productoService = productoService;
    }


    @PostMapping
    public ResponseEntity<ProductoDto> guardar(@RequestBody ProductoCommandInsert productoCommandInsert){

        ProductoDto productoDtoObt = productoService.guardar(ProductoMapper.mapFromCommandInsertToDto(productoCommandInsert));

        return ResponseEntity.ok(productoDtoObt);
        }


    @GetMapping
    public ResponseEntity<ProductoDto> obtenerPorId(@PathVariable Long id){

        ProductoDto productoDtoObt = productoService.obtenerPorId(id);

        return ResponseEntity.ok(productoDtoObt);
    }

    @PutMapping
    public ResponseEntity<ProductoDto> actualizar(@RequestBody ProductoCommandUpdate productoCommandUpdate) {

        ProductoDto productoDtoObt = productoService.actualizar(ProductoMapper.mapFromCommandUpdateToDto(productoCommandUpdate));

        return ResponseEntity.ok(productoDtoObt);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> desactivar(@PathVariable Long id){
        try {
            ProductoDto producto = productoService.obtenerPorId(id);
            if (producto == null)
                return ResponseEntity.notFound().build();

            producto.declararDisponibilidad(EstadoD.INACTIVO);
            productoService.guardar(producto);
            return ResponseEntity.ok("Se desactivó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar (@PathVariable Long id) {
        String respuesta = productoService.eliminar(id);

        return ResponseEntity.ok(respuesta);
    }

}
