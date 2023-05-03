package com.jma.productoservice.service.impl;

import com.jma.productoservice.api.EmpleadoResponse;
import com.jma.productoservice.dto.EmpleadoDto;
import com.jma.productoservice.entity.EmpleadoEntity;
import com.jma.productoservice.entity.UsuarioEntity;
import com.jma.productoservice.mapping.EmpleadoMapper;
import com.jma.productoservice.mapping.UsuarioMapper;
import com.jma.productoservice.repository.EmpleadoRepository;
import com.jma.productoservice.repository.UsuarioRepository;
import com.jma.productoservice.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements EmpleadoService<EmpleadoDto> {

    private final EmpleadoRepository empleadoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository,
                               UsuarioRepository usuarioRepository){
        this.empleadoRepository = empleadoRepository;
        this.usuarioRepository = usuarioRepository;
    }



    @Override
    public EmpleadoResponse obtenerTodosP(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<EmpleadoEntity> empleadospageable = empleadoRepository.findAll(pageable);

        List<EmpleadoEntity> empleados = empleadospageable.getContent();

        List<EmpleadoDto> content = empleados.stream().map(EmpleadoMapper::mapToDto).toList();

        EmpleadoResponse empleadoResponse = new EmpleadoResponse();

        empleadoResponse.setContent(content);
        empleadoResponse.setPageNo(empleadospageable.getNumber());
        empleadoResponse.setPageSize(empleadospageable.getSize());
        empleadoResponse.setTotalElements(empleadospageable.getTotalElements());
        empleadoResponse.setTotalPages(empleadospageable.getTotalPages());
        empleadoResponse.setLast(empleadospageable.isLast());
        return empleadoResponse;
    }

    @Override
    public List<EmpleadoDto> guardarTodos(List<EmpleadoDto> list) {

        List<UsuarioEntity> usuarioEntities = list.stream().map( e -> usuarioRepository.findById(e.getUsuarioDto().getId()).orElse(null)).toList();
        List<EmpleadoEntity> empleadoEntities = list.stream().map(EmpleadoMapper::mapToEntity).toList();

        for (int i = 0; i < empleadoEntities.size(); i++) {
            empleadoEntities.get(i).setUsuario(usuarioEntities.get(i));
        }
        List<EmpleadoEntity> empleadosGuardados = empleadoRepository.saveAll(empleadoEntities);
        return getEmpleadoDtosWithSetUsuarioDto(empleadosGuardados);
    }

    private List<EmpleadoDto> getEmpleadoDtosWithSetUsuarioDto(List<EmpleadoEntity> empleadosGuardados) {
        List<EmpleadoDto> empleadosDtoObt = empleadosGuardados.stream().map(EmpleadoMapper::mapToDto).toList();

        for (int i = 0; i < empleadosDtoObt.size(); i++) {
            empleadosDtoObt.get(i).setUsuarioDto(UsuarioMapper.mapToDto(empleadosGuardados.get(i).getUsuario()));
        }

        return empleadosDtoObt;
    }

    @Override
    public EmpleadoDto guardar(EmpleadoDto object) {

        UsuarioEntity usuarioEntity = usuarioRepository.findById(object.getUsuarioDto().getId()).orElse(null);
        //validación para cortar flujo
        EmpleadoEntity empleadoEntityObt = empleadoRepository.save(EmpleadoMapper.mapToEntity(object));
        empleadoEntityObt.setUsuario(usuarioEntity);

        return EmpleadoMapper.mapToDto(empleadoEntityObt);
    }

    @Override
    public List<EmpleadoDto> obtenerTodos() {

        List<EmpleadoEntity> empleadoEntities = empleadoRepository.findAll();
        return getEmpleadoDtosWithSetUsuarioDto(empleadoEntities);
    }

    @Override
    public String eliminar(Object id) {
        try{
            empleadoRepository.deleteById((Long)id);
            return "Fue eliminado con éxito";
        }catch (Exception ex){
            return "No se pudo eliminar, se encontró un error";
        }
    }

    @Override
    public EmpleadoDto obtenerPorId(Object id) {
        EmpleadoEntity empleadoEntity = empleadoRepository.findById((Long)id).orElse(new EmpleadoEntity());
        EmpleadoDto empleadoDto = EmpleadoMapper.mapToDto(empleadoEntity);
        empleadoDto.setUsuarioDto(UsuarioMapper.mapToDto(empleadoEntity.getUsuario()));

        return empleadoDto;
    }

    @Override
    public EmpleadoDto actualizar(EmpleadoDto object) {
        EmpleadoEntity empleadoEntity = EmpleadoMapper.mapToEntity(object);
        empleadoEntity.setId(object.getId());

        return EmpleadoMapper.mapToDto(empleadoRepository.save(empleadoEntity));
    }
}
