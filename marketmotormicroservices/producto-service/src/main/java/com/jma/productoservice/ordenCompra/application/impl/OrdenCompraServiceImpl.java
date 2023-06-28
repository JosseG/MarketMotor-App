package com.jma.productoservice.ordenCompra.application.impl;

import com.jma.productoservice.detalleOrdenCompra.application.service.DetalleOrdenCompraService;
import com.jma.productoservice.detalleOrdenCompra.domain.dto.DetalleOrdenCompraDto;
import com.jma.productoservice.detalleVenta.domain.dto.DetalleVentaDto;
import com.jma.productoservice.empleado.infrastructure.out.EmpleadoRepository;
import com.jma.productoservice.ordenCompra.domain.dto.OrdenCompraDto;
import com.jma.productoservice.empleado.domain.entity.EmpleadoEntity;
import com.jma.productoservice.ordenCompra.domain.entity.OrdenCompraEntity;
import com.jma.productoservice.ordenCompra.domain.response.OrdenCompraResponse;
import com.jma.productoservice.ordenCompra.infrastructure.out.OrdenCompraRepository;
import com.jma.productoservice.producto.application.mapper.ProductoMapper;
import com.jma.productoservice.producto.application.service.ProductoService;
import com.jma.productoservice.producto.domain.dto.ProductoDto;
import com.jma.productoservice.producto.domain.entity.ProductoEntity;
import com.jma.productoservice.producto.domain.response.ProductoResponse;
import com.jma.productoservice.proveedor.domain.entity.ProveedorEntity;
import com.jma.productoservice.empleado.application.mapper.EmpleadoMapper;
import com.jma.productoservice.ordenCompra.application.mapper.OrdenCompraMapper;
import com.jma.productoservice.proveedor.application.mapper.ProveedorMapper;
import com.jma.productoservice.ordenCompra.application.service.OrdenCompraService;
import com.jma.productoservice.proveedor.infrastructure.out.ProveedorRepository;
import com.jma.productoservice.utils.MyException;
import com.jma.productoservice.venta.domain.dto.VentaDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrdenCompraServiceImpl implements OrdenCompraService<OrdenCompraDto> {

    private final OrdenCompraRepository ordenCompraRepository;
    private final ProveedorRepository proveedorRepository;
    private final EmpleadoRepository empleadoRepository;
    private final DetalleOrdenCompraService<DetalleOrdenCompraDto> detalleOrdenCompraService;
    private final ProductoService<ProductoDto> productoService;

    /*
    @Autowired
    public OrdenCompraServiceImpl(OrdenCompraRepository ordenCompraRepository,
                                  ProveedorRepository proveedorRepository,
                                  EmpleadoRepository empleadoRepository) {
        this.ordenCompraRepository = ordenCompraRepository;
        this.proveedorRepository = proveedorRepository;
        this.empleadoRepository = empleadoRepository;
    }
    */
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

    @Override
    public OrdenCompraResponse obtenerPendientesPaginados(boolean esPendiente, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<OrdenCompraEntity> ordenCompraEntitiespageable = ordenCompraRepository.findOrdenCompraEntitiesByConfirmado(esPendiente,pageable);

        List<OrdenCompraEntity> ordenescompra = ordenCompraEntitiespageable.getContent();

        List<OrdenCompraDto> content = ordenescompra.stream().map(OrdenCompraMapper::mapToDto).toList();

        OrdenCompraResponse ordenCompraResponse = new OrdenCompraResponse();

        ordenCompraResponse.setContent(content);
        ordenCompraResponse.setPageNo(ordenCompraEntitiespageable.getNumber());
        ordenCompraResponse.setPageSize(ordenCompraEntitiespageable.getSize());
        ordenCompraResponse.setTotalElements(ordenCompraEntitiespageable.getTotalElements());
        ordenCompraResponse.setTotalPages(ordenCompraEntitiespageable.getTotalPages());
        ordenCompraResponse.setLast(ordenCompraEntitiespageable.isLast());
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




    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean realizarOrdenCompra(OrdenCompraDto a, List<DetalleOrdenCompraDto> detalles) throws MyException {

        OrdenCompraDto ordenCompraRealizada = guardar(a);

        for(DetalleOrdenCompraDto detalle: detalles){
            detalle.getOrdenCompra().setId(ordenCompraRealizada.getId());
            DetalleOrdenCompraDto detalleGuardado = detalleOrdenCompraService.guardar(detalle);
            if(!detalleGuardado.getProducto().isEstado()){
                throw new MyException("Producto inhabilitado");
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean confirmarCompra(Long id, List<DetalleOrdenCompraDto> a) throws MyException  {

        OrdenCompraDto ordenCompraDto = obtenerPorId(id);


        if(ordenCompraDto == null || a.size()<1)
            throw new MyException("Error en encontrar ordencompra");

        ordenCompraDto.setConfirmado(true);
        guardar(ordenCompraDto);
        for(DetalleOrdenCompraDto detalle: a){
            ProductoDto productoEncontrado = productoService.obtenerPorId(detalle.getProducto().getId());
            productoEncontrado.setStock(productoEncontrado.getStock() + detalle.getCantidad());
            productoService.actualizar(productoEncontrado);
        }
        return false;
    }
}
