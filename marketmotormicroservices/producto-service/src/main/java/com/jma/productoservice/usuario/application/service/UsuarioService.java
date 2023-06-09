package com.jma.productoservice.usuario.application.service;


import com.jma.productoservice.service.common.ICrudCommon;
import com.jma.productoservice.usuario.domain.command.UsuarioCommandLogin;
import com.jma.productoservice.usuario.domain.response.UserAuthenticateResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface UsuarioService<T> extends ICrudCommon<T> {

    List<T> guardarTodos(List<T> list);

    T getUsuarioByAlias(String username);

    UserAuthenticateResponse authenticate(UsuarioCommandLogin request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
