package com.jma.productoservice.detalleOrdenCompra.domain.response;

import com.jma.productoservice.detalleOrdenCompra.domain.dto.DetalleOrdenCompraDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleOrdenCompraResponse {

    private List<DetalleOrdenCompraDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}
