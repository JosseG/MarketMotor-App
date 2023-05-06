package com.jma.productoservice.repository;

import com.jma.productoservice.entity.PermisoEntity;
import com.jma.productoservice.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<RolEntity,Long> {

   /* @Query(value = "SELECT t1.* FROM tb_rol t1 INNER JOIN tb_permiso_rol t2 ON t1.id_rol = t2.id_rol WHERE t2.id_permiso = ?1 ",nativeQuery = true)
    List<RolEntity> findRolEntitiesByPermisoId(Long permisoId);*/

       //List<RolEntity> findByPermisos(PermisoEntity permisoId);
    @Query(value = "SELECT t1 FROM RolEntity t1 join fetch t1.permisos t2 WHERE t2.id = ?1 ")
    List<RolEntity> findRolEntitiesByPermisoId(Long permisoId);

}
