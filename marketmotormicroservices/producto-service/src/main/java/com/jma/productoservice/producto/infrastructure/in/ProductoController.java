package com.jma.productoservice.producto.infrastructure.in;
import com.jma.productoservice.producto.domain.command.ProductoCommandInsert;
import com.jma.productoservice.producto.domain.command.ProductoCommandUpdate;
import com.jma.productoservice.producto.domain.dto.ProductoDto;
import com.jma.productoservice.producto.application.mapper.ProductoMapper;
import com.jma.productoservice.producto.application.service.ProductoService;
import com.jma.productoservice.producto.domain.response.ProductoResponse;
import com.jma.productoservice.utils.ConstantsService;
import com.jma.productoservice.utils.EstadoD;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@Validated
@AllArgsConstructor
public class ProductoController {

    private final ProductoService<ProductoDto> productoService;

    /*
    @Autowired
    public ProductoController(ProductoService<ProductoDto> productoService){
        this.productoService = productoService;
    }
*/


    @PostMapping
    public ResponseEntity<ProductoDto> guardar(@RequestBody @Valid ProductoCommandInsert productoCommandInsert){

        ProductoDto productoDtoObt = productoService.guardar(ProductoMapper.mapFromCommandInsertToDto(productoCommandInsert));

        return ResponseEntity.ok(productoDtoObt);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDto>> obtenerTodos(){

        List<ProductoDto> productoDtos = productoService.obtenerTodos();

        return ResponseEntity.ok(productoDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> obtenerPorId(@PathVariable Long id){

        ProductoDto productoDtoObt = productoService.obtenerPorId(id);

        return ResponseEntity.ok(productoDtoObt);
    }

    @PutMapping
    public ResponseEntity<ProductoDto> actualizar(@RequestBody  @Valid ProductoCommandUpdate productoCommandUpdate) {

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

    @GetMapping("/pagination")
    public ResponseEntity<ProductoResponse> obtenerTodosPaginados(
            @RequestParam(value = "pageNo", defaultValue = ConstantsService.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ConstantsService.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ConstantsService.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ConstantsService.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return ResponseEntity.ok(productoService.obtenerTodosPaginados(pageNo-1, pageSize, sortBy, sortDir));
    }


    @GetMapping("/pagination/{descripcion}")
    public ResponseEntity<ProductoResponse> obtenerProductosFiltradosPaginados(@PathVariable String descripcion,
            @RequestParam(value = "pageNo", defaultValue = ConstantsService.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ConstantsService.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ConstantsService.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ConstantsService.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return ResponseEntity.ok(productoService.obtenerPFiltradosPorDescripcion(descripcion,pageNo-1, pageSize, sortBy, sortDir));
    }

}
