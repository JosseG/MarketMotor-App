package com.jma.productoservice.proveedor.infrastructure.in;


import com.jma.productoservice.proveedor.domain.command.ProveedorCommandInsert;
import com.jma.productoservice.proveedor.domain.command.ProveedorCommandUpdate;
import com.jma.productoservice.proveedor.domain.dto.ProveedorDto;
import com.jma.productoservice.proveedor.application.mapper.ProveedorMapper;
import com.jma.productoservice.proveedor.application.service.ProveedorService;
import com.jma.productoservice.proveedor.domain.response.ProveedorResponse;
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
@RequestMapping("/proveedores")
@Validated
@AllArgsConstructor
public class ProveedorController {

    private final ProveedorService<ProveedorDto> proveedorService;

    /*
    @Autowired
    public ProveedorController(ProveedorService<ProveedorDto> proveedorService){
        this.proveedorService = proveedorService;
    }
*/
    @GetMapping
    public ResponseEntity<List<ProveedorDto>> obtenerTodos(){
        return ResponseEntity.ok(proveedorService.obtenerTodos());
    }



    @PostMapping
    public ResponseEntity<ProveedorDto> guardar(@RequestBody @Valid ProveedorCommandInsert proveedorCommandInsert){

        ProveedorDto proveedorDtoObt = proveedorService.guardar(ProveedorMapper.mapFromCommandInsertToDto(proveedorCommandInsert));

        return ResponseEntity.ok(proveedorDtoObt);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDto> obtenerPorId(@PathVariable Long id){

        ProveedorDto proveedorDtoObt = proveedorService.obtenerPorId(id);

        return ResponseEntity.ok(proveedorDtoObt);
    }

    @PutMapping
    public ResponseEntity<ProveedorDto> actualizar(@RequestBody @Valid ProveedorCommandUpdate proveedorCommandUpdate) {

        ProveedorDto proveedorDtoObt = proveedorService.actualizar(ProveedorMapper.mapFromCommandUpdateToDto(proveedorCommandUpdate));

        return ResponseEntity.ok(proveedorDtoObt);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> desactivar(@PathVariable Long id){
        try {
            ProveedorDto proveedor = proveedorService.obtenerPorId(id);
            if (proveedor == null)
                return ResponseEntity.notFound().build();

            proveedor.setId(id);
            proveedor.declararDisponibilidad(EstadoD.INACTIVO);
            proveedorService.guardar(proveedor);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar (@PathVariable Long id) {
        String respuesta = proveedorService.eliminar(id);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/pagination")
    public ResponseEntity<ProveedorResponse> obtenerTodosPaginados(
            @RequestParam(value = "pageNo", defaultValue = ConstantsService.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ConstantsService.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ConstantsService.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ConstantsService.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return ResponseEntity.ok(proveedorService.obtenerTodosPaginados(pageNo-1, pageSize, sortBy, sortDir));
    }

}