package com.jma.productoservice.detalleOrdenCompra.infrastructure.in;

import com.jma.productoservice.detalleOrdenCompra.domain.command.DetalleOrdenCompraCommandInsert;
import com.jma.productoservice.detalleOrdenCompra.domain.command.DetalleOrdenCompraCommandUpdate;
import com.jma.productoservice.detalleOrdenCompra.domain.dto.DetalleOrdenCompraDto;
import com.jma.productoservice.detalleOrdenCompra.domain.response.DetalleOrdenCompraResponse;
import com.jma.productoservice.ordenCompra.domain.dto.OrdenCompraDto;
import com.jma.productoservice.producto.domain.dto.ProductoDto;
import com.jma.productoservice.detalleOrdenCompra.application.mapper.DetalleOrdenCompraMapper;
import com.jma.productoservice.detalleOrdenCompra.application.service.DetalleOrdenCompraService;
import com.jma.productoservice.utils.ConstantsService;
import com.jma.productoservice.utils.EstadoD;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleordencompra")
@Validated
public class DetalleOrdenCompraController {

    private final DetalleOrdenCompraService<DetalleOrdenCompraDto> detalleOrdenCompraService;

    @Autowired
    public DetalleOrdenCompraController(DetalleOrdenCompraService<DetalleOrdenCompraDto> detalleOrdenCompraService){
        this.detalleOrdenCompraService = detalleOrdenCompraService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleOrdenCompraDto>> obtenerTodos(){
        return ResponseEntity.ok(detalleOrdenCompraService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleOrdenCompraDto> obtenerPorId(@PathVariable("id") Long id){
        DetalleOrdenCompraDto detalleOrdenCompraDto = detalleOrdenCompraService.obtenerPorId(id);
        if(detalleOrdenCompraDto == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(detalleOrdenCompraDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> desactivar(@PathVariable("id") Long id){

        try{
            DetalleOrdenCompraDto detalleOrdenCompraDto = detalleOrdenCompraService.obtenerPorId(id);
            if(detalleOrdenCompraDto == null)
                return ResponseEntity.notFound().build();

            detalleOrdenCompraDto.declararDisponibilidad(EstadoD.INACTIVO);
            detalleOrdenCompraService.guardar(detalleOrdenCompraDto);
            return ResponseEntity.ok("Se desactivó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id){
        try{
            String respuesta = detalleOrdenCompraService.eliminar(id);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<DetalleOrdenCompraDto> guardar(@RequestBody @Valid DetalleOrdenCompraCommandInsert detalleOrdenCompraCommandInsert){
        try{
            ProductoDto productoDto = new ProductoDto();
            productoDto.setId(detalleOrdenCompraCommandInsert.getIdProducto());

            OrdenCompraDto ordenCompraDto = new OrdenCompraDto();
            ordenCompraDto.setId(detalleOrdenCompraCommandInsert.getIdOrdenCompra());


            DetalleOrdenCompraDto detalleToSave = DetalleOrdenCompraMapper.mapFromCommandInsertToDto(detalleOrdenCompraCommandInsert);
            detalleToSave.setProducto(productoDto);
            detalleToSave.setOrdenCompra(ordenCompraDto);
            DetalleOrdenCompraDto detalleGuardado = detalleOrdenCompraService.guardar(detalleToSave);
            return ResponseEntity.ok(detalleGuardado);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/pagination")
    public ResponseEntity<DetalleOrdenCompraResponse> obtenerTodosPaginados(
            @RequestParam(value = "pageNo", defaultValue = ConstantsService.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ConstantsService.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ConstantsService.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ConstantsService.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return ResponseEntity.ok(detalleOrdenCompraService.obtenerTodosPaginados(pageNo-1, pageSize, sortBy, sortDir));
    }

    @PutMapping
    public ResponseEntity<DetalleOrdenCompraDto> actualizar(@RequestBody @Valid DetalleOrdenCompraCommandUpdate detalleOrdenCompraCommandUpdate){

        DetalleOrdenCompraDto detalleOrdenCompraDto = DetalleOrdenCompraMapper.mapFromCommandUpdateToDto(detalleOrdenCompraCommandUpdate);
        DetalleOrdenCompraDto detalleActualizado = detalleOrdenCompraService.actualizar(detalleOrdenCompraDto);

        return ResponseEntity.ok(detalleActualizado);
    }


}
