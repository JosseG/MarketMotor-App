package com.jma.productoservice.cliente.infrastructure.out;

import com.jma.productoservice.cliente.domain.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity,Long> {
}
