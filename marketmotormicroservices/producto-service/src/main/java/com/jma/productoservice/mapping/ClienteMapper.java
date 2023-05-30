package com.jma.productoservice.mapping;

import com.jma.productoservice.api.cliente.ClienteCommandInsert;
import com.jma.productoservice.dto.ClienteDto;
import com.jma.productoservice.entity.ClienteEntity;
import com.jma.productoservice.utils.EstadoD;

public class ClienteMapper {


    public static ClienteDto mapToDto(ClienteEntity clienteEntity){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(clienteEntity.getId());
        clienteDto.setDni(clienteEntity.getDni());
        clienteDto.setApellido(clienteEntity.getApellido());
        clienteDto.setNombre(clienteEntity.getNombre());
        clienteDto.setActualizadoEn(clienteEntity.getActualizadoEn());
        clienteDto.setCreadoEn(clienteEntity.getCreadoEn());
        clienteDto.setEstado(clienteEntity.isEstado());
        return clienteDto;
    }

    public static ClienteEntity mapToEntity(ClienteDto clienteDto){
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setDni(clienteDto.getDni());
        clienteEntity.setApellido(clienteDto.getApellido());
        clienteEntity.setNombre(clienteDto.getNombre());
        clienteEntity.setEstado(clienteDto.isEstado());
        return clienteEntity;
    }


    public static ClienteDto mapFromCommandInsertToDto(ClienteCommandInsert clienteCommandInsert){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setDni(clienteCommandInsert.getDni());
        clienteDto.setApellido(clienteCommandInsert.getApellido());
        clienteDto.setNombre(clienteCommandInsert.getNombre());
        clienteDto.declararDisponibilidad(EstadoD.ACTIVO);
        return clienteDto;
    }

    
}
