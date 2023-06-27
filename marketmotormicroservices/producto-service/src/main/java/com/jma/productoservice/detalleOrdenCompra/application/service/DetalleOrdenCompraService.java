package com.jma.productoservice.detalleOrdenCompra.application.service;

import com.jma.productoservice.detalleOrdenCompra.domain.response.DetalleOrdenCompraResponse;
import com.jma.productoservice.utils.ICrudCommon;

public interface DetalleOrdenCompraService<T> extends ICrudCommon<T> {

    DetalleOrdenCompraResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

    DetalleOrdenCompraResponse obtenerPaginadosPorOrdenCompra(Long id,int pageNo, int pageSize, String sortBy, String sortDir);



    DetalleOrdenCompraResponse obtenerPaginadosPorFiltroEmpleadoId(Long id,int pageNo, int pageSize, String sortBy, String sortDir);


    DetalleOrdenCompraResponse obtenerPaginadosPorFiltroProductoId(Long id,int pageNo, int pageSize, String sortBy, String sortDir);
}
