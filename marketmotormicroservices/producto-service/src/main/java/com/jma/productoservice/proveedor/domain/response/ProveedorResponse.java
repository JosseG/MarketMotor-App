package com.jma.productoservice.proveedor.domain.response;


import com.jma.productoservice.proveedor.domain.dto.ProveedorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorResponse {

    private List<ProveedorDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}
