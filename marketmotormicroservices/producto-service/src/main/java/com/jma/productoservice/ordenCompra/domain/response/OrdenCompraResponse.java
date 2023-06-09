package com.jma.productoservice.ordenCompra.domain.response;


import com.jma.productoservice.ordenCompra.domain.dto.OrdenCompraDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenCompraResponse {

    private List<OrdenCompraDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}
