package com.jma.productoservice.empleado.application.impl;

import com.jma.productoservice.empleado.domain.dto.EmpleadoDto;
import com.jma.productoservice.empleado.domain.entity.EmpleadoEntity;
import com.jma.productoservice.empleado.domain.response.EmpleadoResponse;
import com.jma.productoservice.empleado.infrastructure.out.EmpleadoRepository;
import com.jma.productoservice.usuario.domain.entity.UsuarioEntity;
import com.jma.productoservice.empleado.application.mapper.EmpleadoMapper;
import com.jma.productoservice.usuario.application.mapper.UsuarioMapper;
import com.jma.productoservice.empleado.application.service.EmpleadoService;
import com.jma.productoservice.usuario.infrastructure.out.UsuarioRepository;
import jakarta.transaction.Transactional;
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
public class EmpleadoServiceImpl implements EmpleadoService<EmpleadoDto> {

    private final EmpleadoRepository empleadoRepository;
    private final UsuarioRepository usuarioRepository;

    /*
    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository,
                               UsuarioRepository usuarioRepository){
        this.empleadoRepository = empleadoRepository;
        this.usuarioRepository = usuarioRepository;
    }
    */
    @Override
    public EmpleadoResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<EmpleadoEntity> empleadospageable = empleadoRepository.findAll(pageable);

        List<EmpleadoEntity> empleados = empleadospageable.getContent();

        List<EmpleadoDto> content = getEmpleadoDtosWithSetUsuarioDto(empleados);

        EmpleadoResponse empleadoResponse = new EmpleadoResponse();

        empleadoResponse.setContent(content);
        empleadoResponse.setPageSize(empleadospageable.getSize());
        empleadoResponse.setPageNo(empleadospageable.getNumber());
        empleadoResponse.setTotalElements(empleadospageable.getTotalElements());
        empleadoResponse.setTotalPages(empleadospageable.getTotalPages());
        empleadoResponse.setLast(empleadospageable.isLast());
        return empleadoResponse;
    }

    @Override
    public EmpleadoDto obtenerEmpleadoPorUsuarioAlias(String alias) {
        return empleadoRepository.findEmpleadoEntityByUserAlias(alias).map(EmpleadoMapper::mapToDto).orElse(null);
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
    @Transactional
    public EmpleadoDto guardar(EmpleadoDto object) {

        UsuarioEntity usuarioEntity = usuarioRepository.findById(object.getUsuarioDto().getId()).orElse(null);


        //validación para cortar flujo
        EmpleadoEntity empleadoEntityObt = EmpleadoMapper.mapToEntity(object);

        if (object.getId()!= null) {
            empleadoEntityObt.setId(object.getId());
        }

        System.out.println("El objeto entiti usuario" + usuarioEntity.getAlias());

        empleadoEntityObt.setUsuario(usuarioEntity);
        EmpleadoEntity empleadoEntitysaved = empleadoRepository.save(empleadoEntityObt);
        EmpleadoDto empleadoDto = EmpleadoMapper.mapToDto(empleadoEntitysaved);
        empleadoDto.setUsuarioDto(UsuarioMapper.mapToDto(empleadoEntitysaved.getUsuario()));


        return empleadoDto;
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
        Optional<EmpleadoEntity> opcional = empleadoRepository.findById(object.getId());
        EmpleadoEntity empleado;

        if(opcional.isPresent()){
            empleado = opcional.get();
            empleado.setNombre(object.getNombre());
            empleado.setApellidoMaterno(object.getApellidoMaterno());
            empleado.setApellidoPaterno(object.getApellidoPaterno());
            empleado.setTelefono(object.getTelefono());
            empleado.setCorreo(object.getCorreo());
            return EmpleadoMapper.mapToDto(empleadoRepository.save(empleado));
        }

        return null;
    }





}
