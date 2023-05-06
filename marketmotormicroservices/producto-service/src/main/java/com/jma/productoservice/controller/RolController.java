package com.jma.productoservice.controller;


import com.jma.productoservice.api.rol.RolCommandInsert;
import com.jma.productoservice.api.rol.RolCommandUpdate;
import com.jma.productoservice.dto.PermisoDto;
import com.jma.productoservice.dto.RolDto;
import com.jma.productoservice.mapping.RolMapper;
import com.jma.productoservice.service.PermisoService;
import com.jma.productoservice.service.RolService;
import com.jma.productoservice.utils.EstadoD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolService<RolDto> rolService;
    private final PermisoService<PermisoDto> permisoService;

    @Autowired
    public RolController(RolService<RolDto> rolService,
                         PermisoService<PermisoDto> permisoService){
        this.rolService = rolService;
        this.permisoService = permisoService;
    }


    @PostMapping
    public ResponseEntity<RolDto> guardar(@RequestBody RolCommandInsert rolCommandInsert){

        RolDto rolDtoObt = rolService.guardar(RolMapper.mapFromCommandInsertToDto(rolCommandInsert));
        return ResponseEntity.ok(rolDtoObt);

    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDto> obtenerPorId(@PathVariable(name = "id") Long id){

        RolDto rolDtoObt = rolService.obtenerPorId(id);

        return ResponseEntity.ok(rolDtoObt);
    }

    @GetMapping()
    public ResponseEntity<List<RolDto>> obtenerTodos(){

        List<RolDto> roles = rolService.obtenerTodos();

        return ResponseEntity.ok(roles);
    }

    @PutMapping
    public ResponseEntity<RolDto> actualizar(@RequestBody RolCommandUpdate rolCommandUpdate) {

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

    @PostMapping("/{idRol}/permisos/{idPermiso}")
    public ResponseEntity<PermisoDto> definirPermisoARol(@PathVariable(value = "idRol") Long idRol, @PathVariable(value = "idPermiso") Long idPermiso) {
        PermisoDto permisoRolConfigured = rolService.definirPermiso(idRol,idPermiso);
        return ResponseEntity.ok(permisoRolConfigured);
    }

    @DeleteMapping("/{id_rol}/permisos/{id_permiso}")
    public ResponseEntity<RolDto> removerPermiso(@PathVariable(value = "id_rol") Long idRol, @PathVariable(value = "id_permiso") Long idPermiso) {
        RolDto rol = rolService.removerPermiso(idRol,idPermiso);
        return ResponseEntity.ok(rol);
    }


    @GetMapping("/permisos/{permisoId}/roles")
    public ResponseEntity<List<RolDto>> obtenerTodosRolesPorPermisoId(@PathVariable(value = "permisoId") Long permisoId) {
        if (permisoService.obtenerPorId(permisoId)==null) {
            throw new RuntimeException("No fue encontrado el permiso" + permisoId);
        }

        List<RolDto> rolesObt = rolService.buscarRolesPorPermisosId(permisoId);
        return ResponseEntity.ok(rolesObt);
    }


    @GetMapping("/{rolId}/permisos")
    public ResponseEntity<List<PermisoDto>> obtenerTodosPermisosPorRolId(@PathVariable(value = "rolId") Long rolId) {
        if (rolService.obtenerPorId(rolId)==null) {
            throw new RuntimeException("No fue encontrado el rol = " + rolId);
        }

        List<PermisoDto> permisosObt = permisoService.buscarPermisosPorRolId(rolId);
        return ResponseEntity.ok(permisosObt);
    }




}
