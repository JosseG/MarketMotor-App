package com.jma.productoservice.detalleVenta.application.service;

import com.jma.productoservice.detalleOrdenCompra.domain.response.DetalleOrdenCompraResponse;
import com.jma.productoservice.detalleVenta.domain.dto.DetalleVentaDto;
import com.jma.productoservice.detalleVenta.domain.response.DetalleVentaResponse;
import com.jma.productoservice.utils.ICrudCommon;

import java.util.List;

public interface DetalleVentaService <T> extends ICrudCommon<T> {

    DetalleVentaResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

    DetalleVentaResponse obtenerPaginadosPorFiltroEmpleadoId(Long id, int pageNo, int pageSize, String sortBy, String sortDir);


    DetalleVentaResponse obtenerPaginadosPorFiltroProductoId(Long id,int pageNo, int pageSize, String sortBy, String sortDir);


    List<DetalleVentaDto> obtenerPorFiltroProductoId(Long id);

}
