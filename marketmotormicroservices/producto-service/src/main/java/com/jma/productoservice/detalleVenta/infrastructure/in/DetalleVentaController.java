package com.jma.productoservice.detalleVenta.infrastructure.in;

import com.jma.productoservice.detalleVenta.application.mapper.DetalleVentaMapper;
import com.jma.productoservice.detalleVenta.application.service.DetalleVentaService;
import com.jma.productoservice.detalleVenta.domain.command.DetalleVentaCommandInsert;
import com.jma.productoservice.detalleVenta.domain.command.DetalleVentaCommandUpdate;
import com.jma.productoservice.detalleVenta.domain.dto.DetalleVentaDto;
import com.jma.productoservice.detalleVenta.domain.response.DetalleVentaResponse;
import com.jma.productoservice.producto.domain.dto.ProductoDto;
import com.jma.productoservice.utils.ConstantsService;
import com.jma.productoservice.utils.EstadoD;
import com.jma.productoservice.venta.domain.dto.VentaDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleventa")
@Validated
@AllArgsConstructor
public class DetalleVentaController {

    private final DetalleVentaService<DetalleVentaDto> detalleVentaService;
    @GetMapping
    public ResponseEntity<List<DetalleVentaDto>> obtenerTodosDetalle(){
        return ResponseEntity.ok(detalleVentaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaDto> obtenerPorIdDetalle(@PathVariable("id") Long id){
        DetalleVentaDto detalleVentaDto = detalleVentaService.obtenerPorId(id);
        if(detalleVentaDto == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(detalleVentaDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> desactivarDetalle(@PathVariable("id") Long id){

        try{
            DetalleVentaDto detalleVentaDto = detalleVentaService.obtenerPorId(id);
            if(detalleVentaDto == null)
                return ResponseEntity.notFound().build();

            detalleVentaDto.declararDisponibilidad(EstadoD.INACTIVO);
            detalleVentaService.guardar(detalleVentaDto);
            return ResponseEntity.ok("Se desactivó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDetalle(@PathVariable("id") Long id){
        try{
            String respuesta = detalleVentaService.eliminar(id);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<DetalleVentaDto> guardarDetalle(@RequestBody @Valid DetalleVentaCommandInsert detalleVentaCommandInsert){
        try{
            ProductoDto productoDto = new ProductoDto();
            productoDto.setId(detalleVentaCommandInsert.getIdProducto());

            VentaDto ventaDto = new VentaDto();
            ventaDto.setId(detalleVentaCommandInsert.getIdVenta());


            DetalleVentaDto detalleToSave = DetalleVentaMapper.mapFromCommandInsertToDto(detalleVentaCommandInsert);
            detalleToSave.setProducto(productoDto);
            detalleToSave.setVenta(ventaDto);
            DetalleVentaDto detalleGuardado = detalleVentaService.guardar(detalleToSave);
            return ResponseEntity.ok(detalleGuardado);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/pagination")
    public ResponseEntity<DetalleVentaResponse> obtenerTodosPaginadosDetalle(
            @RequestParam(value = "pageNo", defaultValue = ConstantsService.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ConstantsService.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ConstantsService.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ConstantsService.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return ResponseEntity.ok(detalleVentaService.obtenerTodosPaginados(pageNo-1, pageSize, sortBy, sortDir));
    }

    @PutMapping
    public ResponseEntity<DetalleVentaDto> actualizar(@RequestBody @Valid DetalleVentaCommandUpdate detalleVentaCommandUpdate){

        DetalleVentaDto detalleVentaDto = DetalleVentaMapper.mapFromCommandUpdateToDto(detalleVentaCommandUpdate);
        DetalleVentaDto detalleActualizado = detalleVentaService.actualizar(detalleVentaDto);

        return ResponseEntity.ok(detalleActualizado);
    }

}
