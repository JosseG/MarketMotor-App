package com.jma.productoservice.service.impl;

import com.jma.productoservice.api.EmpleadoResponse;
import com.jma.productoservice.dto.EmpleadoDto;
import com.jma.productoservice.entity.EmpleadoEntity;
import com.jma.productoservice.mapping.EmpleadoMapper;
import com.jma.productoservice.repository.EmpleadoRepository;
import com.jma.productoservice.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService<EmpleadoEntity> {

    private final EmpleadoRepository empleadoRepository;
    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository){
        this.empleadoRepository = empleadoRepository;
    }
    @Override
    public EmpleadoEntity guardar(EmpleadoEntity object) {
        return empleadoRepository.save(object);
    }

    @Override
    public List<EmpleadoEntity> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    @Override
    public void eliminar(Object id) {
        EmpleadoEntity empleadoEntity = empleadoRepository.findById((Long)id).orElse(new EmpleadoEntity());

        empleadoRepository.delete(empleadoEntity);
    }

    @Override
    public EmpleadoEntity obtenerPorId(Object id) {

        if(empleadoRepository.findById((Long)id).isEmpty()){
            return new EmpleadoEntity();
        }
        return empleadoRepository.findById((Long)id).get();
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
}
