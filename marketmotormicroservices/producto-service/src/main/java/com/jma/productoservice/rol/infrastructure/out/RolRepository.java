package com.jma.productoservice.rol.infrastructure.out;

import com.jma.productoservice.rol.domain.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepository extends JpaRepository<RolEntity,Long> {


}
