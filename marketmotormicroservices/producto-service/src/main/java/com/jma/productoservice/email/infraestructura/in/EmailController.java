package com.jma.productoservice.email.infraestructura.in;

import com.jma.productoservice.email.application.EmailService;
import com.jma.productoservice.proveedor.application.service.ProveedorService;
import com.jma.productoservice.proveedor.domain.dto.ProveedorDto;
import com.jma.productoservice.usuario.application.service.UsuarioService;
import com.jma.productoservice.usuario.domain.dto.UsuarioDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/emails")
@AllArgsConstructor
@Validated
public class EmailController {

    private final EmailService emailService;
    private final ProveedorService<ProveedorDto> proveedorService;


    @GetMapping("/sendEmail/{id}")
    public ResponseEntity<Boolean> enviarAviso(@PathVariable Long id) throws IOException {

        System.out.println("llego aqui");
        //System.out.println(correo);

        var proveedor = proveedorService.obtenerPorId(id);
        System.out.println(proveedor.getCorreo());
        emailService.sendTextEmail(proveedor.getCorreo());

        return ResponseEntity.ok(true);
    }
}
