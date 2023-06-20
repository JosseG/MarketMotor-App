package com.jma.productoservice.rol.infrastructure.in;


import com.jma.productoservice.rol.domain.command.RolCommandInsert;
import com.jma.productoservice.rol.domain.command.RolCommandUpdate;
import com.jma.productoservice.rol.domain.dto.RolDto;
import com.jma.productoservice.rol.application.mapper.RolMapper;
import com.jma.productoservice.rol.application.service.RolService;
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
@RequestMapping("/roles")
@Validated
@AllArgsConstructor
public class RolController {

    private final RolService<RolDto> rolService;

    /*
    @Autowired
    public RolController(RolService<RolDto> rolService
                         ){
        this.rolService = rolService;
    }
*/

    @PostMapping
    public ResponseEntity<RolDto> guardar(@RequestBody @Valid RolCommandInsert rolCommandInsert){

        RolDto rolDtoObt = rolService.guardar(RolMapper.mapFromCommandInsertToDto(rolCommandInsert));
        return ResponseEntity.ok(rolDtoObt);

    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDto> obtenerPorId(@PathVariable(name = "id") Long id){

        RolDto rolDtoObt = rolService.obtenerPorId(id);

        return ResponseEntity.ok(rolDtoObt);
    }

    @GetMapping
    public ResponseEntity<List<RolDto>> obtenerTodos(){

        List<RolDto> roles = rolService.obtenerTodos();

        return ResponseEntity.ok(roles);
    }

    @PutMapping
    public ResponseEntity<RolDto> actualizar(@RequestBody  @Valid RolCommandUpdate rolCommandUpdate) {

        RolDto rolDtoObt = rolService.actualizar(RolMapper.mapFromCommandUpdateToDto(rolCommandUpdate));

        return ResponseEntity.ok(rolDtoObt);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> desactivar(@PathVariable(name = "id") Long id){

        try{
            RolDto rol = rolService.obtenerPorId(id);
            if(rol == null)
                return ResponseEntity.notFound().build();

            rol.declararDisponibilidad(EstadoD.INACTIVO);
            rolService.guardar(rol);
            return ResponseEntity.ok("Se desactivó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable(name = "id") Long id) {
        String respuesta = rolService.eliminar(id);
        return ResponseEntity.ok(respuesta);
    }


    @PostMapping("/guardarTodos")
    public ResponseEntity<List<RolDto>> guardarTodos(@RequestBody @Valid List<RolCommandInsert> rolCommandInserts){

        List<RolDto> rolesMapeados = rolCommandInserts.stream().map(RolMapper::mapFromCommandInsertToDto).toList();
        List<RolDto> rolesGuardados = rolService.guardarTodos(rolesMapeados);

        return ResponseEntity.ok(rolesGuardados);

    }

}
