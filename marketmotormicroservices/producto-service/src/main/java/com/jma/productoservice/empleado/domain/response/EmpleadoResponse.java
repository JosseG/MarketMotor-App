package com.jma.productoservice.empleado.domain.response;

import com.jma.productoservice.empleado.domain.dto.EmpleadoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoResponse {

    private List<EmpleadoDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;


}
