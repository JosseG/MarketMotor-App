package com.jma.productoservice.ordenCompra.application.service;

import com.jma.productoservice.detalleOrdenCompra.domain.dto.DetalleOrdenCompraDto;
import com.jma.productoservice.ordenCompra.domain.dto.OrdenCompraDto;
import com.jma.productoservice.ordenCompra.domain.response.OrdenCompraResponse;
import com.jma.productoservice.utils.ICrudCommon;
import com.jma.productoservice.utils.MyException;

import java.util.List;

public interface OrdenCompraService<T> extends ICrudCommon<T> {

    OrdenCompraResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

    OrdenCompraResponse obtenerPendientesPaginados(boolean esPendiente, int pageNo, int pageSize, String sortBy, String sortDir);

    boolean realizarOrdenCompra(T a, List<DetalleOrdenCompraDto> detalles) throws MyException;


    boolean confirmarCompra(Long id,List<DetalleOrdenCompraDto> a) throws MyException;
}
