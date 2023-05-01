package com.jma.productoservice.controller;

import com.jma.productoservice.api.EmpleadoResponse;
import com.jma.productoservice.entity.EmpleadoEntity;
import com.jma.productoservice.service.EmpleadoService;
import com.jma.productoservice.utils.ConstantsService;
import com.jma.productoservice.utils.EstadoD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService<EmpleadoEntity> empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService<EmpleadoEntity> empleadoService){
        this.empleadoService = empleadoService;
    }

    @GetMapping()
    public ResponseEntity<List<EmpleadoEntity>> obtenerTodos(){
        return ResponseEntity.ok(empleadoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoEntity> obtenerPorId(@PathVariable("id") Long id){
        EmpleadoEntity empleado = empleadoService.obtenerPorId(id);
        if(empleado == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(empleado);
    }

    @PatchMapping("/desactivar/{id}")
    public ResponseEntity<String> desactivar(@PathVariable("id") Long id){

        try{
            EmpleadoEntity empleado = empleadoService.obtenerPorId(id);
            if(empleado == null)
                return ResponseEntity.notFound().build();

            empleado.declararDisponibilidad(EstadoD.INACTIVO);
            empleadoService.guardar(empleado);

            return ResponseEntity.ok("Se desactivó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id){
        try{
            empleadoService.eliminar(id);
            return ResponseEntity.ok("Se eliminó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<EmpleadoEntity> guardar(@RequestBody EmpleadoEntity empleado){
        try{
            EmpleadoEntity empleadoGuardado = empleadoService.guardar(empleado);
            return ResponseEntity.ok(empleadoGuardado);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public EmpleadoResponse obtenerTodosP(
            @RequestParam(value = "pageNo", defaultValue = ConstantsService.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ConstantsService.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ConstantsService.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ConstantsService.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return empleadoService.obtenerTodosP(pageNo, pageSize, sortBy, sortDir);
    }


}
