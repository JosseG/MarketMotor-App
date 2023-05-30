package com.jma.productoservice.service.impl;

import com.jma.productoservice.api.DetalleVentaResponse;
import com.jma.productoservice.dto.DetalleVentaDto;
import com.jma.productoservice.entity.DetalleVentaEntity;
import com.jma.productoservice.entity.ProductoEntity;
import com.jma.productoservice.entity.VentaEntity;
import com.jma.productoservice.mapping.DetalleVentaMapper;
import com.jma.productoservice.mapping.ProductoMapper;
import com.jma.productoservice.mapping.VentaMapper;
import com.jma.productoservice.repository.DetalleVentaRepository;
import com.jma.productoservice.repository.ProductoRepository;
import com.jma.productoservice.repository.VentaRepository;
import com.jma.productoservice.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService<DetalleVentaDto> {


    private final DetalleVentaRepository detalleVentaRepository;
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public DetalleVentaServiceImpl(DetalleVentaRepository detalleVentaRepository, VentaRepository ventaRepository, ProductoRepository productoRepository){
        this.detalleVentaRepository = detalleVentaRepository;
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
    }
    @Override
    public DetalleVentaDto guardar(DetalleVentaDto object) {

        VentaEntity ventaEntity = ventaRepository.findById(object.getVenta().getId()).orElse(null);
        ProductoEntity productoEntity = productoRepository.findById(object.getProducto().getId()).orElse(null);

        DetalleVentaEntity detalleVentaEntity = DetalleVentaMapper.mapToEntity(object);

        if(object.getId()!= null){
            detalleVentaEntity.setId(object.getId());
        }

        detalleVentaEntity.setVenta(ventaEntity);
        detalleVentaEntity.setProducto(productoEntity);

        DetalleVentaEntity detalleEntityGuardado = detalleVentaRepository.save(detalleVentaEntity);
        DetalleVentaDto detalleDtoGuardado = DetalleVentaMapper.mapToDto(detalleEntityGuardado);

        detalleDtoGuardado.setProducto(ProductoMapper.mapToDto(detalleVentaEntity.getProducto()));
        detalleDtoGuardado.setVenta(VentaMapper.mapToDto(detalleVentaEntity.getVenta()));

        return detalleDtoGuardado;
    }

    @Override
    public List<DetalleVentaDto> obtenerTodos() {

        List<DetalleVentaEntity> detallesOrdenEntity = detalleVentaRepository.findAll();
        List<DetalleVentaDto> detallesDtoMapped = detallesOrdenEntity.stream().map(DetalleVentaMapper::mapToDto).toList();

        for (int i = 0; i < detallesDtoMapped.size(); i++) {
            detallesDtoMapped.get(i).setVenta(VentaMapper.mapToDto(detallesOrdenEntity.get(i).getVenta()));
            detallesDtoMapped.get(i).setProducto(ProductoMapper.mapToDto(detallesOrdenEntity.get(i).getProducto()));
        }

        return detallesDtoMapped;
    }

    @Override
    public String eliminar(Object id) {
        try{
            detalleVentaRepository.deleteById((Long)id);
            return "Fue eliminado con éxito";
        }catch (Exception ex){
            return "No se pudo eliminar, se encontró un error";
        }
    }

    @Override
    public DetalleVentaDto obtenerPorId(Object id) {
        DetalleVentaEntity detalleVentaEntity = detalleVentaRepository.findById((Long)id).orElse(new DetalleVentaEntity());
        DetalleVentaDto detalleVentaDto = DetalleVentaMapper.mapToDto(detalleVentaEntity);
        detalleVentaDto.setProducto(ProductoMapper.mapToDto(detalleVentaEntity.getProducto()));
        detalleVentaDto.setVenta(VentaMapper.mapToDto(detalleVentaEntity.getVenta()));

        return detalleVentaDto;
    }

    @Override
    public DetalleVentaDto actualizar(DetalleVentaDto object) {
        Optional<DetalleVentaEntity> opcional = detalleVentaRepository.findById(object.getId());
        DetalleVentaEntity detalleVenta;

        if(opcional.isPresent()){
            detalleVenta = opcional.get();
            detalleVenta.setUnidades(object.getUnidades());
            return DetalleVentaMapper.mapToDto(detalleVentaRepository.save(detalleVenta));
        }

        return null;
    }

    @Override
    public DetalleVentaResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<DetalleVentaEntity> detalleOrdenPageable = detalleVentaRepository.findAll(pageable);

        List<DetalleVentaEntity> detallesOrden = detalleOrdenPageable.getContent();

        List<DetalleVentaDto> content = detallesMapeados(detallesOrden);

        DetalleVentaResponse detalleVentaResponse = new DetalleVentaResponse();

        detalleVentaResponse.setContent(content);
        detalleVentaResponse.setPageNo(detalleOrdenPageable.getNumber());
        detalleVentaResponse.setPageSize(detalleOrdenPageable.getSize());
        detalleVentaResponse.setTotalElements(detalleOrdenPageable.getTotalElements());
        detalleVentaResponse.setLast(detalleOrdenPageable.isLast());
        detalleVentaResponse.setTotalPages(detalleOrdenPageable.getTotalPages());
        return detalleVentaResponse;
    }

    private List<DetalleVentaDto> detallesMapeados(List<DetalleVentaEntity> detallesOrdenes) {
        List<DetalleVentaDto> content = detallesOrdenes.stream().map(DetalleVentaMapper::mapToDto).toList();

        for (int i = 0; i < content.size(); i++) {
            content.get(i).setProducto(ProductoMapper.mapToDto(detallesOrdenes.get(i).getProducto()));
            content.get(i).setVenta(VentaMapper.mapToDto(detallesOrdenes.get(i).getVenta()));
        }
        return content;
    }
}
