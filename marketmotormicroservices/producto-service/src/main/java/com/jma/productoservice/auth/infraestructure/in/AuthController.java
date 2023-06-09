package com.jma.productoservice.auth.infraestructure.in;

import com.jma.productoservice.usuario.domain.command.UsuarioCommandLogin;
import com.jma.productoservice.usuario.domain.dto.UsuarioDto;
import com.jma.productoservice.usuario.application.service.UsuarioService;
import com.jma.productoservice.usuario.domain.response.UserAuthenticateResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    private final UsuarioService<UsuarioDto> usuarioService;

    @Autowired
    public AuthController(UsuarioService<UsuarioDto> usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/signin")
    public ResponseEntity<UserAuthenticateResponse> signin(
            @RequestBody @Valid UsuarioCommandLogin loginCommand
    ) {
        return ResponseEntity.ok(usuarioService.authenticate(loginCommand));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        usuarioService.refreshToken(request, response);
    }

}