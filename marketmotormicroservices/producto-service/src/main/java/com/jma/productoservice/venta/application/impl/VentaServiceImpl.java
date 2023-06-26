package com.jma.productoservice.venta.application.impl;

import com.jma.productoservice.cliente.infrastructure.out.ClienteRepository;
import com.jma.productoservice.empleado.infrastructure.out.EmpleadoRepository;
import com.jma.productoservice.venta.domain.dto.VentaDto;
import com.jma.productoservice.cliente.domain.entity.ClienteEntity;
import com.jma.productoservice.empleado.domain.entity.EmpleadoEntity;
import com.jma.productoservice.venta.domain.entity.VentaEntity;
import com.jma.productoservice.cliente.application.mapper.ClienteMapper;
import com.jma.productoservice.empleado.application.mapper.EmpleadoMapper;
import com.jma.productoservice.venta.application.mapper.VentaMapper;
import com.jma.productoservice.venta.application.service.VentaService;
import com.jma.productoservice.venta.domain.response.VentaResponse;
import com.jma.productoservice.venta.infrastructure.out.VentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VentaServiceImpl implements VentaService<VentaDto> {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;

    /*
    @Autowired
    public VentaServiceImpl(VentaRepository ventaRepository,
                            ClienteRepository clienteRepository, EmpleadoRepository empleadoRepository){
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
    }

     */

    @Override
    public VentaDto guardar(VentaDto object) {

        ClienteEntity clienteEntity = clienteRepository.findById(object.getCliente().getId()).orElse(null);
        EmpleadoEntity empleadoEntity = empleadoRepository.findById(object.getEmpleado().getId()).orElse(null);

        VentaEntity ventaEntity = VentaMapper.mapToEntity(object);

        if (object.getId() != null) {
            ventaEntity.setId(object.getId());
        }
        ventaEntity.setCliente(clienteEntity);
        ventaEntity.setEmpleado(empleadoEntity);

        VentaEntity ventaSaved = ventaRepository.save(ventaEntity);
        VentaDto ventaDtoMapped = VentaMapper.mapToDto(ventaSaved);

        ventaDtoMapped.setCliente(ClienteMapper.mapToDto(ventaSaved.getCliente()));
        ventaDtoMapped.setEmpleado(EmpleadoMapper.mapToDto(ventaSaved.getEmpleado()));

        return ventaDtoMapped;
    }

    @Override
    public List<VentaDto> obtenerTodos() {
        List<VentaEntity> ventaEntities = ventaRepository.findAll();

        return ordenesMapeadas(ventaEntities);
    }

    @Override
    public String eliminar(Object id) {
        try{
            ventaRepository.deleteById((Long)id);
            return "Fue eliminado con éxito";
        }catch (Exception ex){
            return "No se pudo eliminar, se encontró un error";
        }
    }

    @Override
    public VentaDto obtenerPorId(Object id) {
        VentaEntity ventaEntity = ventaRepository.findById((Long)id).orElse(null);
        if(ventaEntity==null){
            return null;
        }
        VentaDto ventaDto = VentaMapper.mapToDto(ventaEntity);
        ventaDto.setEmpleado(EmpleadoMapper.mapToDto(ventaEntity.getEmpleado()));
        ventaDto.setCliente(ClienteMapper.mapToDto(ventaEntity.getCliente()));

        return ventaDto;
    }

    @Override
    public VentaDto actualizar(VentaDto object) {
        Optional<VentaEntity> opcional = ventaRepository.findById(object.getId());
        VentaEntity venta;

        if (opcional.isPresent()) {
            venta = opcional.get();
            venta.setPreciototal(object.getPreciototal());
            return VentaMapper.mapToDto(ventaRepository.save(venta));
        }

        return null;
    }

    @Override
    public VentaResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<VentaEntity> ordenPageable = ventaRepository.findAll(pageable);

        List<VentaEntity> ordenes = ordenPageable.getContent();

        List<VentaDto> content = ordenesMapeadas(ordenes);

        VentaResponse ventaResponse = new VentaResponse();

        ventaResponse.setContent(content);
        ventaResponse.setPageNo(ordenPageable.getNumber());
        ventaResponse.setPageSize(ordenPageable.getSize());
        ventaResponse.setTotalElements(ordenPageable.getTotalElements());
        ventaResponse.setTotalPages(ordenPageable.getTotalPages());
        ventaResponse.setLast(ordenPageable.isLast());
        return ventaResponse;
    }

    private List<VentaDto> ordenesMapeadas(List<VentaEntity> ordenes) {
        List<VentaDto> content = ordenes.stream().map(VentaMapper::mapToDto).toList();

        for (int i = 0; i < content.size(); i++) {
            content.get(i).setCliente(ClienteMapper.mapToDto(ordenes.get(i).getCliente()));
            content.get(i).setEmpleado(EmpleadoMapper.mapToDto(ordenes.get(i).getEmpleado()));
        }
        return content;
    }
}
