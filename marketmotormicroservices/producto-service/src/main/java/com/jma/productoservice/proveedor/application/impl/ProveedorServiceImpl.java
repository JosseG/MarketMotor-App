package com.jma.productoservice.proveedor.application.impl;

import com.jma.productoservice.proveedor.domain.dto.ProveedorDto;
import com.jma.productoservice.proveedor.domain.entity.ProveedorEntity;
import com.jma.productoservice.proveedor.domain.response.ProveedorResponse;
import com.jma.productoservice.proveedor.infrastructure.out.ProveedorRepository;
import com.jma.productoservice.usuario.domain.entity.UsuarioEntity;
import com.jma.productoservice.proveedor.application.mapper.ProveedorMapper;
import com.jma.productoservice.usuario.application.mapper.UsuarioMapper;
import com.jma.productoservice.proveedor.application.service.ProveedorService;
import com.jma.productoservice.usuario.infrastructure.out.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService<ProveedorDto> {

    private final ProveedorRepository proveedorRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ProveedorServiceImpl(ProveedorRepository proveedorRepository,
                                UsuarioRepository usuarioRepository) { this. proveedorRepository = proveedorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<ProveedorDto> guardarTodos(List<ProveedorDto> list) {
        List<UsuarioEntity> usuarioEntities = list.stream().map( e -> usuarioRepository.findById(e.getUsuarioDto().getId()).orElse(null)).toList();
        List<ProveedorEntity> proveedorEntities = list.stream().map(ProveedorMapper::mapToEntity).toList();

        for (int i = 0; i < proveedorEntities.size(); i++) {
            proveedorEntities.get(i).setUsuario(usuarioEntities.get(i));
        }
        List<ProveedorEntity> proveedoresGuardados = proveedorRepository.saveAll(proveedorEntities);
        return proveedoresMapeados(proveedoresGuardados);
    }

    @Override
    @Transactional
    public ProveedorDto guardar(ProveedorDto object) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(object.getUsuarioDto().getId()).orElse(null);
        ProveedorEntity proveedorEntity = ProveedorMapper.mapToEntity(object);

        if (object.getId()!= null) {
            proveedorEntity.setId(object.getId());
        }

        proveedorEntity.setUsuario(usuarioEntity);

        ProveedorEntity proveedorEntitySaved = proveedorRepository.save(proveedorEntity);
        ProveedorDto proveedorSavedDto = ProveedorMapper.mapToDto(proveedorEntitySaved);
        proveedorSavedDto.setUsuarioDto(UsuarioMapper.mapToDto(proveedorEntitySaved.getUsuario()));


        return proveedorSavedDto;
    }

    @Override
    public List<ProveedorDto> obtenerTodos(){
        List<ProveedorEntity> proveedorEntities = proveedorRepository.findAll();
        return proveedoresMapeados(proveedorEntities);
    }

    @Override
    public String eliminar(Object id) {
        try {
            proveedorRepository.deleteById((Long)id);
            return "Fue eliminado copn éxito";
        } catch (Exception ex){
            return "No se pudo eliminar,se encontró un error";
        }
    }

    @Override
    public ProveedorDto obtenerPorId(Object id) {
        ProveedorEntity proveedorEntity = proveedorRepository.findById((Long)id).orElse(new ProveedorEntity());
        ProveedorDto proveedorDto = ProveedorMapper.mapToDto(proveedorEntity);
        proveedorDto.setUsuarioDto(UsuarioMapper.mapToDto(proveedorEntity.getUsuario()));

        return proveedorDto;
    }

    @Override
    public  ProveedorDto actualizar(ProveedorDto object) {
        Optional<ProveedorEntity> opcional = proveedorRepository.findById(object.getId());
        ProveedorEntity proveedor;

        if(opcional.isPresent()){
            proveedor = opcional.get();
            proveedor.setNumeroRuc(object.getNumeroRuc());
            proveedor.setNombreComercial(object.getNombreComercial());
            proveedor.setRazonSocial(object.getRazonSocial());
            proveedor.setCorreo(object.getCorreo());
            proveedor.setDireccion(object.getDireccion());
            proveedor.setDepartamento(object.getDepartamento());
            proveedor.setTelefonoProveedor(object.getTelefonoProveedor());
            return ProveedorMapper.mapToDto(proveedorRepository.save(proveedor));
        }

        return null;

    }


    @Override
    public ProveedorResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<ProveedorEntity> proveedoresPageable = proveedorRepository.findAll(pageable);

        List<ProveedorEntity> proveedores = proveedoresPageable.getContent();

        List<ProveedorDto> content = proveedoresMapeados(proveedores);

        ProveedorResponse proveedorResponse = new ProveedorResponse();

        proveedorResponse.setContent(content);
        proveedorResponse.setPageSize(proveedoresPageable.getSize());
        proveedorResponse.setTotalElements(proveedoresPageable.getTotalElements());
        proveedorResponse.setPageNo(proveedoresPageable.getNumber());
        proveedorResponse.setTotalPages(proveedoresPageable.getTotalPages());
        proveedorResponse.setLast(proveedoresPageable.isLast());
        return proveedorResponse;
    }

    private List<ProveedorDto> proveedoresMapeados(List<ProveedorEntity> proveedores) {
        List<ProveedorDto> content = proveedores.stream().map(ProveedorMapper::mapToDto).toList();

        for (int i = 0; i < content.size(); i++) {
            content.get(i).setUsuarioDto(UsuarioMapper.mapToDto(proveedores.get(i).getUsuario()));
        }
        return content;
    }

}
