package com.viheakode.backend.repository;

import com.viheakode.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = """
            SELECT r.*
            FROM roles r
            JOIN user_roles ur ON ur.role_id = r.role_id
            WHERE ur.user_id = :userId
            """, nativeQuery = true)
    List<Role> findAllByUserId(@Param("userId") Long userId);

    boolean existsByRoleName(String roleName);
    boolean existsByRoleNameAndRoleIdNot(String roleName, Long roleId);
}
