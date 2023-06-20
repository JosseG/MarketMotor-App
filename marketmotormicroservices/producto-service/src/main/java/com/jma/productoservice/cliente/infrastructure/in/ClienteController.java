package com.jma.productoservice.cliente.infrastructure.in;


import com.jma.productoservice.cliente.domain.command.ClienteCommandInsert;
import com.jma.productoservice.cliente.domain.dto.ClienteDto;
import com.jma.productoservice.cliente.application.mapper.ClienteMapper;
import com.jma.productoservice.cliente.application.service.ClienteService;
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
@RequestMapping("/clientes")
@Validated
@AllArgsConstructor
public class ClienteController {

    private final ClienteService<ClienteDto> clienteService;

    /*@Autowired
    public ClienteController(ClienteService<ClienteDto> ventaService){
        this.clienteService = ventaService;
    }
*/

    @PostMapping
    public ResponseEntity<ClienteDto> guardar(@RequestBody @Valid ClienteCommandInsert clienteCommandInsert){

        ClienteDto clienteDto = clienteService.guardar(ClienteMapper.mapFromCommandInsertToDto(clienteCommandInsert));

        return ResponseEntity.ok(clienteDto);
    }

    /*@PostMapping("/guardarTodos")
    public ResponseEntity<List<ClienteDto>> guardarTodos(@RequestBody @Valid List<ClienteCommandInsert> clienteCommandInserts){

        List<ClienteDto> clientesMappeados = clienteCommandInserts.stream().map(ClienteMapper::mapFromCommandInsertToDto).toList();
        List<ClienteDto> clientesGuardados = clienteService.guardarTodos(clientesMappeados);

        return ResponseEntity.ok(clientesGuardados);

    }*/

    @GetMapping
    public ResponseEntity<List<ClienteDto>> obtenerTodos(){

        List<ClienteDto> clienteDtos = clienteService.obtenerTodos();

        return ResponseEntity.ok(clienteDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerPorId(@PathVariable Long id){

        ClienteDto clienteDto = clienteService.obtenerPorId(id);

        return ResponseEntity.ok(clienteDto);
    }


    /*@PutMapping
    public ResponseEntity<ClienteDto> actualizar(@RequestBody @Valid PermisoCommandUpdate clienteCommandUpdate){

        ClienteDto clienteDto = clienteService.actualizar(ClienteMapper.mapFromCommandUpdateToDto(clienteCommandUpdate));

        return ResponseEntity.ok(clienteDto);
    }*/


    @PatchMapping("/{id}")
    public ResponseEntity<String> desactivar(@PathVariable Long id){

        try{
            ClienteDto cliente = clienteService.obtenerPorId(id);
            if(cliente == null)
                return ResponseEntity.notFound().build();

            cliente.declararDisponibilidad(EstadoD.INACTIVO);
            clienteService.guardar(cliente);
            return ResponseEntity.ok("Se desactivó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){

        String respuesta = clienteService.eliminar(id);

        return ResponseEntity.ok(respuesta);
    }



}
