package com.jma.productoservice.venta.infrastructure.in;


import com.jma.productoservice.cliente.domain.dto.ClienteDto;
import com.jma.productoservice.detalleVenta.domain.command.DetalleVentaCommandInsert;
import com.jma.productoservice.detalleVenta.domain.command.DetalleVentaCommandUpdate;
import com.jma.productoservice.detalleVenta.domain.dto.DetalleVentaDto;
import com.jma.productoservice.detalleVenta.domain.response.DetalleVentaResponse;
import com.jma.productoservice.empleado.domain.dto.EmpleadoDto;
import com.jma.productoservice.detalleVenta.application.mapper.DetalleVentaMapper;
import com.jma.productoservice.venta.application.mapper.VentaMapper;
import com.jma.productoservice.producto.domain.dto.ProductoDto;
import com.jma.productoservice.detalleVenta.application.service.DetalleVentaService;
import com.jma.productoservice.venta.application.service.VentaService;
import com.jma.productoservice.utils.ConstantsService;
import com.jma.productoservice.utils.EstadoD;
import com.jma.productoservice.venta.domain.command.VentaCommandInsert;
import com.jma.productoservice.venta.domain.command.VentaCommandUpdate;
import com.jma.productoservice.venta.domain.dto.VentaDto;
import com.jma.productoservice.venta.domain.response.VentaResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@Validated
@AllArgsConstructor
public class VentaController {

    private final VentaService<VentaDto> ventaService;

    /*@Autowired
    public VentaController(VentaService<VentaDto> ventaService,DetalleVentaService<DetalleVentaDto> detalleVentaService){
        this.ventaService = ventaService;
        this.detalleVentaService = detalleVentaService;
    }
*/

    @GetMapping
    public ResponseEntity<List<VentaDto>> obtenerTodos(){
        return ResponseEntity.ok(ventaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDto> obtenerPorId(@PathVariable("id") Long id){
        VentaDto ventaDto = ventaService.obtenerPorId(id);
        if(ventaDto == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(ventaDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> desactivar(@PathVariable("id") Long id){

        try{
            VentaDto ventaDto = ventaService.obtenerPorId(id);
            if(ventaDto == null)
                return ResponseEntity.notFound().build();

            ventaDto.setId(id);
            ventaDto.declararDisponibilidad(EstadoD.INACTIVO);
            ventaService.guardar(ventaDto);
            return ResponseEntity.ok("Se desactivó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id){
        try{
            String respuesta = ventaService.eliminar(id);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<VentaDto> guardar(@RequestBody @Valid VentaCommandInsert ventaCommandInsert){
        try{
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setId(ventaCommandInsert.getIdCliente());

            EmpleadoDto empleadoDto = new EmpleadoDto();
            empleadoDto.setId(ventaCommandInsert.getIdEmpleado());
            
            VentaDto ventaToSave = VentaMapper.mapFromCommandInsertToDto(ventaCommandInsert);
            ventaToSave.setCliente(clienteDto);
            ventaToSave.setEmpleado(empleadoDto);
            VentaDto ventaGuardado = ventaService.guardar(ventaToSave);
            return ResponseEntity.ok(ventaGuardado);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/pagination")
    public ResponseEntity<VentaResponse> obtenerTodosPaginados(
            @RequestParam(value = "pageNo", defaultValue = ConstantsService.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ConstantsService.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ConstantsService.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ConstantsService.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return ResponseEntity.ok(ventaService.obtenerTodosPaginados(pageNo, pageSize, sortBy, sortDir));
    }

    @PutMapping
    public ResponseEntity<VentaDto> actualizar(@RequestBody @Valid VentaCommandUpdate ventaCommandUpdate){

        VentaDto ventaDto = VentaMapper.mapFromCommandUpdateToDto(ventaCommandUpdate);
        VentaDto ventaActualizado = ventaService.actualizar(ventaDto);

        return ResponseEntity.ok(ventaActualizado);
    }

}
