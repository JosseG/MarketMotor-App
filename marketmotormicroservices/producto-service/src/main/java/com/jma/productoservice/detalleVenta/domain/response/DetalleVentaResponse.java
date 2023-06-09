package com.jma.productoservice.detalleVenta.domain.response;

import com.jma.productoservice.detalleVenta.domain.dto.DetalleVentaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaResponse {

    private List<DetalleVentaDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}
