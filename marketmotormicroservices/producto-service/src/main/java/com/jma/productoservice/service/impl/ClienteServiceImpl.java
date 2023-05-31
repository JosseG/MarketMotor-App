package com.jma.productoservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jma.productoservice.api.ClienteResponse;
import com.jma.productoservice.api.ClienteResponse;
import com.jma.productoservice.api.UserAuthenticateResponse;
import com.jma.productoservice.dto.ClienteDto;
import com.jma.productoservice.dto.ClienteDto;
import com.jma.productoservice.entity.ClienteEntity;
import com.jma.productoservice.entity.ClienteEntity;
import com.jma.productoservice.entity.UsuarioEntity;
import com.jma.productoservice.mapping.ClienteMapper;
import com.jma.productoservice.mapping.ClienteMapper;
import com.jma.productoservice.mapping.UsuarioMapper;
import com.jma.productoservice.repository.ClienteRepository;
import com.jma.productoservice.repository.TokenRepository;
import com.jma.productoservice.repository.UsuarioRepository;
import com.jma.productoservice.service.ClienteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService<ClienteDto> {


    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDto guardar(ClienteDto object) {
        ClienteEntity clienteEntity = ClienteMapper.mapToEntity(object);

        if(object.getId()!= null){
            clienteEntity.setId(object.getId());
        }

        return ClienteMapper.mapToDto(clienteRepository.save(clienteEntity));
    }

    @Override
    public List<ClienteDto> obtenerTodos() {
        return clienteRepository.findAll().stream().map(ClienteMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public String eliminar(Object id) {
        try{
            clienteRepository.deleteById((Long)id);
            return "Fue eliminado con éxito";
        }catch (Exception ex){
            return "No se pudo eliminar, se encontró un error";
        }
    }

    @Override
    public ClienteDto obtenerPorId(Object id) {
        return clienteRepository.findById((Long)id).map(ClienteMapper::mapToDto).orElse(null);
    }

    @Override
    public ClienteDto actualizar(ClienteDto object) {
        ClienteEntity clienteEntity = ClienteMapper.mapToEntity(object);
        clienteEntity.setId(object.getId());

        return ClienteMapper.mapToDto(clienteRepository.save(clienteEntity));
    }

    @Override
    public ClienteResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<ClienteEntity> clientesPageable = clienteRepository.findAll(pageable);

        List<ClienteEntity> clientes = clientesPageable.getContent();

        List<ClienteDto> content = clientes.stream().map(ClienteMapper::mapToDto).collect(Collectors.toList());

        ClienteResponse clienteResponse = new ClienteResponse();

        clienteResponse.setContent(content);
        clienteResponse.setTotalElements(clientesPageable.getTotalElements());
        clienteResponse.setPageSize(clientesPageable.getSize());
        clienteResponse.setPageNo(clientesPageable.getNumber());
        clienteResponse.setTotalPages(clientesPageable.getTotalPages());
        clienteResponse.setLast(clientesPageable.isLast());
        return clienteResponse;
    }








}
