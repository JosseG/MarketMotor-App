package com.jma.productoservice.service.impl;

import com.jma.productoservice.api.OrdenCompraResponse;
import com.jma.productoservice.dto.OrdenCompraDto;
import com.jma.productoservice.entity.EmpleadoEntity;
import com.jma.productoservice.entity.OrdenCompraEntity;
import com.jma.productoservice.entity.ProveedorEntity;
import com.jma.productoservice.mapping.*;
import com.jma.productoservice.repository.EmpleadoRepository;
import com.jma.productoservice.repository.OrdenCompraRepository;
import com.jma.productoservice.repository.ProveedorRepository;
import com.jma.productoservice.service.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenCompraServiceImpl implements OrdenCompraService<OrdenCompraDto> {

    private final OrdenCompraRepository ordenCompraRepository;
    private final ProveedorRepository proveedorRepository;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public OrdenCompraServiceImpl(OrdenCompraRepository ordenCompraRepository,
                                  ProveedorRepository proveedorRepository,
                                  EmpleadoRepository empleadoRepository) {
        this.ordenCompraRepository = ordenCompraRepository;
        this.proveedorRepository = proveedorRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public OrdenCompraDto guardar(OrdenCompraDto object) {

        ProveedorEntity proveedorEntity = proveedorRepository.findById(object.getProveedor().getId()).orElse(null);
        EmpleadoEntity empleadoEntity = empleadoRepository.findById(object.getEmpleado().getId()).orElse(null);

        OrdenCompraEntity ordenCompraEntity = OrdenCompraMapper.mapToEntity(object);

        if (object.getId() != null) {
            ordenCompraEntity.setId(object.getId());
        }
        ordenCompraEntity.setProveedor(proveedorEntity);
        ordenCompraEntity.setEmpleado(empleadoEntity);

        OrdenCompraEntity ordenCompraSaved = ordenCompraRepository.save(ordenCompraEntity);
        OrdenCompraDto ordenCompraDtoMapped = OrdenCompraMapper.mapToDto(ordenCompraSaved);

        ordenCompraDtoMapped.setProveedor(ProveedorMapper.mapToDto(ordenCompraSaved.getProveedor()));
        ordenCompraDtoMapped.setEmpleado(EmpleadoMapper.mapToDto(ordenCompraSaved.getEmpleado()));

        return ordenCompraDtoMapped;
    }

    @Override
    public List<OrdenCompraDto> obtenerTodos() {
        List<OrdenCompraEntity> ordenCompraEntities = ordenCompraRepository.findAll();

        return ordenesMapeadas(ordenCompraEntities);
    }

    @Override
    public String eliminar(Object id) {
        try{
            ordenCompraRepository.deleteById((Long)id);
            return "Fue eliminado con éxito";
        }catch (Exception ex){
            return "No se pudo eliminar, se encontró un error";
        }
    }

    @Override
    public OrdenCompraDto obtenerPorId(Object id) {
        OrdenCompraEntity ordenCompraEntity = ordenCompraRepository.findById((Long)id).orElse(new OrdenCompraEntity());
        OrdenCompraDto ordenCompraDto = OrdenCompraMapper.mapToDto(ordenCompraEntity);
        ordenCompraDto.setEmpleado(EmpleadoMapper.mapToDto(ordenCompraEntity.getEmpleado()));
        ordenCompraDto.setProveedor(ProveedorMapper.mapToDto(ordenCompraEntity.getProveedor()));

        return ordenCompraDto;
    }

    @Override
    public OrdenCompraDto actualizar(OrdenCompraDto object) {
        Optional<OrdenCompraEntity> opcional = ordenCompraRepository.findById(object.getId());
        OrdenCompraEntity ordenCompra;

        if (opcional.isPresent()) {
            ordenCompra = opcional.get();
            ordenCompra.setNumero(object.getNumero());
            ordenCompra.setFecha(object.getFecha());
            ordenCompra.setValorTotal(object.getValorTotal());
            return OrdenCompraMapper.mapToDto(ordenCompraRepository.save(ordenCompra));
        }

        return null;
    }

    @Override
    public OrdenCompraResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<OrdenCompraEntity> ordenPageable = ordenCompraRepository.findAll(pageable);

        List<OrdenCompraEntity> ordenes = ordenPageable.getContent();

        List<OrdenCompraDto> content = ordenesMapeadas(ordenes);

        OrdenCompraResponse ordenCompraResponse = new OrdenCompraResponse();

        ordenCompraResponse.setContent(content);
        ordenCompraResponse.setPageNo(ordenPageable.getNumber());
        ordenCompraResponse.setPageSize(ordenPageable.getSize());
        ordenCompraResponse.setTotalElements(ordenPageable.getTotalElements());
        ordenCompraResponse.setTotalPages(ordenPageable.getTotalPages());
        ordenCompraResponse.setLast(ordenPageable.isLast());
        return ordenCompraResponse;
    }

    private List<OrdenCompraDto> ordenesMapeadas(List<OrdenCompraEntity> ordenes) {
        List<OrdenCompraDto> content = ordenes.stream().map(OrdenCompraMapper::mapToDto).toList();

        for (int i = 0; i < content.size(); i++) {
            content.get(i).setProveedor(ProveedorMapper.mapToDto(ordenes.get(i).getProveedor()));
            content.get(i).setEmpleado(EmpleadoMapper.mapToDto(ordenes.get(i).getEmpleado()));
        }
        return content;
    }
}
