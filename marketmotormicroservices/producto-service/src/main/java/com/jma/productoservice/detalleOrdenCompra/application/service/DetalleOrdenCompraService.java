package com.jma.productoservice.detalleOrdenCompra.application.service;

import com.jma.productoservice.detalleOrdenCompra.domain.dto.DetalleOrdenCompraDto;
import com.jma.productoservice.detalleOrdenCompra.domain.response.DetalleOrdenCompraResponse;
import com.jma.productoservice.detalleVenta.domain.dto.DetalleVentaDto;
import com.jma.productoservice.utils.ICrudCommon;

import java.util.List;

public interface DetalleOrdenCompraService<T> extends ICrudCommon<T> {

    DetalleOrdenCompraResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

    DetalleOrdenCompraResponse obtenerPaginadosPorOrdenCompra(Long id,int pageNo, int pageSize, String sortBy, String sortDir);



    DetalleOrdenCompraResponse obtenerPaginadosPorFiltroEmpleadoId(Long id,int pageNo, int pageSize, String sortBy, String sortDir);


    DetalleOrdenCompraResponse obtenerPaginadosPorFiltroProductoId(Long id,int pageNo, int pageSize, String sortBy, String sortDir);


    List<DetalleOrdenCompraDto> obtenerPorFiltroProductoId(Long id);


}
