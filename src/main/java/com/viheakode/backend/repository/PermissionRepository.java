package com.viheakode.backend.repository;

import com.viheakode.backend.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query(value = """
            SELECT p.*
            FROM permissions p
            JOIN role_permissions rp ON rp.permission_id = p.permission_id
            WHERE rp.role_id = :roleId
            """, nativeQuery = true)
    List<Permission> findAllByRoleId(@Param("roleId") Long roleId);

    boolean existsByPermissionName(String permissionName);

    boolean existsByPermissionNameAndPermissionIdNot(String permissionName, Long permissionId);
}
